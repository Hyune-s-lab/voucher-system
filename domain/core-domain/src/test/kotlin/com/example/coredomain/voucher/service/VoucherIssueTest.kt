package com.example.coredomain.voucher.service

import com.example.coredomain.common.type.VoucherStatus
import com.example.coredomain.contract.repository.ContractRepository
import com.example.coredomain.testutil.TestFixture
import com.example.coredomain.voucher.repository.VoucherRepository
import com.example.coredomain.voucherproduct.repository.VoucherProductRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class VoucherIssueTest(
    private val sut: VoucherIssueService,
    private val voucherRepository: VoucherRepository,
    private val contractRepository: ContractRepository,
    private val voucherProductRepository: VoucherProductRepository
) : DescribeSpec({
    describe("상품권을 발행 합니다.") {
        context("존재하는 계약코드, 존재하는 상품권종코드") {
            val (contractCode, voucherProductCode) = "CT0002" to "PD0003"
            val issuedVoucher = sut.issue(contractCode, voucherProductCode)

            val target = voucherRepository.findByCode(issuedVoucher.code)

            it("상태=ISSUED") {
                target!!.status shouldBe VoucherStatus.ISSUED
            }
        }

        context("존재하는 계약코드, 계약기간이 지금보다 이전") {
            val (contractCode, voucherProductCode) = "CT0001" to "PD0001"

            it("IllegalArgumentException - 유효하지 않은 계약기간") {
                val exception = shouldThrow<IllegalArgumentException> {
                    sut.issue(contractCode, voucherProductCode)
                }
                exception.message shouldBe "유효하지 않은 계약기간"
            }
        }

        context("존재하는 계약코드, 계약기간이 지금보다 이후") {
            val (contractCode, voucherProductCode) = "CT0003" to "PD0001"

            it("IllegalArgumentException - 유효하지 않은 계약기간") {
                val exception = shouldThrow<IllegalArgumentException> {
                    sut.issue(contractCode, voucherProductCode)
                }
                exception.message shouldBe "유효하지 않은 계약기간"
            }
        }

        context("존재하지 않는 계약코드") {
            val (contractCode, voucherProductCode) = "CT000X" to "PD0002"

            it("IllegalArgumentException - 존재하지 않는 계약") {
                val exception = shouldThrow<IllegalArgumentException> {
                    sut.issue(contractCode, voucherProductCode)
                }
                exception.message shouldBe "존재하지 않는 계약"
            }
        }

        context("존재하는 계약코드, 대상 상품권종에 없는 상품권종코드") {
            val (contractCode, voucherProductCode) = "CT0002" to "PD0002"

            it("IllegalArgumentException - 유효하지 않은 상품권종") {
                val exception = shouldThrow<IllegalArgumentException> {
                    sut.issue(contractCode, voucherProductCode)
                }
                exception.message shouldBe "유효하지 않은 상품권종"
            }
        }

        context("존재하는 계약코드, 총계약금액 50만원 계약에 초과 발행") {
            val (contractCode, voucherProductCode) = "CT0004" to "PD0003"

            it("10만원 상품권종 5회 발행") {
                repeat(5) {
                    sut.issue(contractCode, voucherProductCode)
                }
            }

            it("IllegalArgumentException - 상품권 발행한도 초과") {
                val exception = shouldThrow<IllegalArgumentException> {
                    sut.issue(contractCode, voucherProductCode)
                }
                exception.message shouldBe "상품권 발행한도 초과"
            }
        }
    }
}) {
    init {
        TestFixture.상품권종.forEach { voucherProductRepository.save(it) }
        TestFixture.계약_전체.forEach { contractRepository.save(it) }
    }

    override suspend fun afterSpec(spec: Spec) {
        super.afterSpec(spec)
        contractRepository.deleteAll()
        voucherProductRepository.deleteAll()
    }
}

