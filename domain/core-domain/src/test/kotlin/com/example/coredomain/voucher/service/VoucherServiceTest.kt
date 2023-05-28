package com.example.coredomain.voucher.service

import com.example.coredomain.contract.repository.ContractRepository
import com.example.coredomain.testutil.TestFixture
import com.example.coredomain.voucherproduct.repository.VoucherProductRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class VoucherServiceTest(
    private val voucherService: VoucherService,
    private val contractRepository: ContractRepository,
    private val voucherProductRepository: VoucherProductRepository
) : DescribeSpec({
    describe("상품권을 발행 합니다.") {
        context("존재하는 계약코드와 상품권종코드를 입력받아 발행 합니다.") {
            val (contractCode, voucherProductCode) = "CT0002" to "PD0003"
            val createVoucher = voucherService.create(contractCode, voucherProductCode)

            it("발행 성공") {
                voucherService.findByCode(createVoucher.code) shouldNotBe null
            }
        }

        context("발행시작일자가 되지 않은 계약코드를 입력받아 발행 합니다.") {
            val (contractCode, voucherProductCode) = "CT0001" to "PD0001"

            val exception = shouldThrow<IllegalArgumentException> {
                voucherService.create(contractCode, voucherProductCode)
            }

            it("IllegalArgumentException - 유효하지 않은 계약") {
                exception.message shouldBe "발행가능기간이 유효하지 않은 계약"
            }
        }


        context("발행종료일자가 지난 계약코드를 입력받아 발행 합니다.") {
            val (contractCode, voucherProductCode) = "CT0003" to "PD0001"

            val exception = shouldThrow<IllegalArgumentException> {
                voucherService.create(contractCode, voucherProductCode)
            }

            it("IllegalArgumentException - 유효하지 않은 계약") {
                exception.message shouldBe "발행가능기간이 유효하지 않은 계약"
            }
        }

        context("존재하지 않는 계약코드를 입력받아 발행 합니다.") {
            val (contractCode, voucherProductCode) = "CT000X" to "PD0002"
            val exception = shouldThrow<IllegalArgumentException> {
                voucherService.create(contractCode, voucherProductCode)
            }

            it("IllegalArgumentException - 존재하지 않는 계약") {
                exception.message shouldBe "존재하지 않는 계약"
            }
        }

        context("발행 가능한 계약이지만 발행 대상 상품권종에 없는 상품권종코드를 입력받아 발행 합니다.") {
            val (contractCode, voucherProductCode) = "CT0002" to "PD0002"
            val exception = shouldThrow<IllegalArgumentException> {
                voucherService.create(contractCode, voucherProductCode)
            }

            it("IllegalArgumentException - 유효하지 않은 상품권종") {
                exception.message shouldBe "유효하지 않은 상품권종"
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

