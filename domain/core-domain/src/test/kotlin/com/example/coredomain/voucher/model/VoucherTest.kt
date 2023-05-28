package com.example.coredomain.voucher.model

import com.example.coredomain.common.type.VoucherStatus
import com.example.coredomain.testutil.TestFixture
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class VoucherTest : DescribeSpec({
    describe("상품권 라이프 사이클을 검증 합니다.") {
        val (contract, product) = TestFixture.계약_계약기간_중[0] to TestFixture.상품권종[0]
        lateinit var vouchers: List<Voucher>

        context("상품권 3장을 발행 합니다.") {
            vouchers = listOf(
                Voucher(contract = contract, product = product),
                Voucher(contract = contract, product = product),
                Voucher(contract = contract, product = product)
            )

            it("상태=ISSUED") {
                vouchers.forEach {
                    it.status shouldBe VoucherStatus.ISSUED
                    it.issuedAt shouldNotBe null
                }
            }

            it("history size=1") {
                vouchers.forEach {
                    it.histories.size shouldBe 1
                }
            }
        }

        context("상품권0 상태 변경: 발행 -> 사용가능") {
            it("상태=USABLE") {

            }

            it("history size=2") {

            }
        }

        context("상품권0 상태 변경: 사용가능 -> 사용완료") {
            it("상태=USED") {

            }

            it("history size=3") {

            }
        }

        context("상품권1 상태 변경: 발행 -> 사용불가") {
            it("상태=UNUSABLE") {

            }

            it("history size=2") {

            }
        }

        context("상품권2 상태 변경: 사용가능 -> 사용불가") {
            it("상태=UNUSABLE") {

            }

            it("history size=3") {

            }
        }
    }
})
