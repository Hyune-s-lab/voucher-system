package com.example.coredomain.voucher.model

import com.example.coredomain.common.type.VoucherStatus
import com.example.coredomain.testutil.TestFixture
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.time.LocalDate

class VoucherTest : DescribeSpec({
    val (contract, product) = TestFixture.계약_계약기간_중[0] to TestFixture.상품권종[0]
    lateinit var vouchers: List<Voucher>

    describe("상품권 3장을 발행 합니다.") {
        vouchers = listOf(
            Voucher(contract = contract, product = product),
            Voucher(contract = contract, product = product),
            Voucher(contract = contract, product = product),
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

    describe("상품권0 상태 변경: 발행 -> 사용완료(예외) -> 사용가능 -> 사용완료 -> 사용가능(예외)") {
        context("발행 -> 사용완료") {
            val exception = shouldThrow<IllegalStateException> {
                vouchers[0].statusToUsed()
            }

            it("IllegalStateException - 변경 불가능한 상품권 상태") {
                exception.message shouldBe "변경 불가능한 상품권 상태"
            }

            it("상태=ISSUED") {
                vouchers[0].status shouldBe VoucherStatus.ISSUED
            }
        }

        context("발행 -> 사용가능") {
            vouchers[0].statusToUsable()

            it("상태=USABLE") {
                vouchers[0].status shouldBe VoucherStatus.USABLE
            }

            it("history size=2") {
                vouchers[0].histories.size shouldBe 2
            }
        }

        context("사용가능 -> 사용완료") {
            vouchers[0].statusToUsed()

            it("상태=USED") {
                vouchers[0].status shouldBe VoucherStatus.USED
            }

            it("history size=3") {
                vouchers[0].histories.size shouldBe 3
            }
        }

        context("사용완료 -> 사용가능") {
            val exception = shouldThrow<IllegalStateException> {
                vouchers[0].statusToUsable()
            }

            it("IllegalStateException - 변경 불가능한 상품권 상태") {
                exception.message shouldBe "변경 불가능한 상품권 상태"
            }

            it("상태=USED") {
                vouchers[0].status shouldBe VoucherStatus.USED
            }
        }
    }

    describe("상품권1 상태 변경: 발행 -> 사용불가") {
        context("발행 -> 사용불가") {
            vouchers[1].statusToUnusable(LocalDate.now().plusYears(1))

            it("상태=UNUSABLE") {
                vouchers[1].status shouldBe VoucherStatus.UNUSABLE
            }

            it("history size=2") {
                vouchers[1].histories.size shouldBe 2
            }
        }
    }

    describe("상품권2 상태 변경: 발행 -> 사용가능 -> 사용불가") {
        context("발행 -> 사용가능") {
            vouchers[2].statusToUsable()

            it("상태=USABLE") {
                vouchers[2].status shouldBe VoucherStatus.USABLE
            }
        }

        context("사용가능 -> 사용불가") {
            vouchers[2].statusToUnusable(LocalDate.now().plusYears(1))

            it("상태=UNUSABLE") {
                vouchers[2].status shouldBe VoucherStatus.UNUSABLE
            }

            it("history size=3") {
                vouchers[2].histories.size shouldBe 3
            }
        }
    }
})
