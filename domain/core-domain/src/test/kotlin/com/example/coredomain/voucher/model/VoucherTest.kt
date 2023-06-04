package com.example.coredomain.voucher.model

import com.example.coredomain.common.type.VoucherStatus
import com.example.coredomain.testutil.TestFixture
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.time.LocalDate

class VoucherTest : DescribeSpec({
    val (contract, product) = TestFixture.계약_유효기간유효[0] to TestFixture.상품권종[0]
    lateinit var vouchers: List<Voucher>

    describe("상품권 4장을 발행 합니다.") {
        vouchers = listOf(
            Voucher(contract = contract, product = product),
            Voucher(contract = contract, product = product),
            Voucher(contract = contract, product = product),
            Voucher(contract = contract, product = product),
        )

        it("상태=ISSUE") {
            vouchers.forEach {
                it.status shouldBe VoucherStatus.ISSUE
                it.issueAt shouldNotBe null
            }
        }

        it("usableStartDate=null, usableEndDate=null") {
            vouchers.forEach {
                it.usableStartDate shouldBe null
                it.usableEndDate shouldBe null
            }
        }

        it("history size=1") {
            vouchers.forEach {
                it.histories.size shouldBe 1
            }
        }
    }

    describe("상품권0 상태 변경: 발행 -> 사용완료(예외) -> 사용가능 -> 사용완료 -> 사용가능(예외)") {
        context("발행 -> 사용완료(예외)") {
            val exception = shouldThrow<IllegalStateException> {
                vouchers[0].statusToUsed()
            }

            it("IllegalStateException - 사용완료로 변경할 수 없는 상태") {
                exception.message shouldBe "사용완료로 변경할 수 없는 상태"
            }

            it("상태=ISSUE") {
                vouchers[0].status shouldBe VoucherStatus.ISSUE
            }
        }

        context("발행 -> 사용가능") {
            vouchers[0].statusToUsable(LocalDate.now().plusYears(1))

            it("상태=USABLE") {
                vouchers[0].status shouldBe VoucherStatus.USABLE
            }

            it("usableStartDate=not null, usableEndDate=not null") {
                vouchers[0].usableStartDate shouldNotBe null
                vouchers[0].usableEndDate shouldNotBe null
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

        context("사용완료 -> 사용가능(예외)") {
            val exception = shouldThrow<IllegalStateException> {
                vouchers[0].statusToUsable(LocalDate.now().plusYears(1))
            }

            it("IllegalStateException - 사용가능으로 변경할 수 없는 상태") {
                exception.message shouldBe "사용가능으로 변경할 수 없는 상태"
            }

            it("상태=USED") {
                vouchers[0].status shouldBe VoucherStatus.USED
            }
        }
    }

    describe("상품권1 상태 변경: 발행 -> 사용불가 -> 사용가능(예외)") {
        context("발행 -> 사용불가") {
            vouchers[1].statusToUnusable()

            it("상태=UNUSABLE") {
                vouchers[1].status shouldBe VoucherStatus.UNUSABLE
            }

            it("history size=2") {
                vouchers[1].histories.size shouldBe 2
            }
        }

        context("사용불가 -> 사용가능(예외)") {
            val exception = shouldThrow<IllegalStateException> {
                vouchers[1].statusToUsable(LocalDate.now().plusYears(1))
            }

            it("IllegalStateException - 사용가능으로 변경할 수 없는 상태") {
                exception.message shouldBe "사용가능으로 변경할 수 없는 상태"
            }

            it("상태=UNUSABLE") {
                vouchers[1].status shouldBe VoucherStatus.UNUSABLE
            }
        }
    }

    describe("상품권2 상태 변경: 발행 -> 사용가능 -> 사용불가") {
        context("발행 -> 사용가능") {
            vouchers[2].statusToUsable(LocalDate.now().plusYears(1))

            it("상태=USABLE") {
                vouchers[2].status shouldBe VoucherStatus.USABLE
            }

            it("usableStartDate=not null, usableEndDate=not null") {
                vouchers[2].usableStartDate shouldNotBe null
                vouchers[2].usableEndDate shouldNotBe null
            }

            it("history size=2") {
                vouchers[2].histories.size shouldBe 2
            }
        }

        context("사용가능 -> 사용불가") {
            vouchers[2].statusToUnusable()

            it("상태=UNUSABLE") {
                vouchers[2].status shouldBe VoucherStatus.UNUSABLE
            }

            it("history size=3") {
                vouchers[2].histories.size shouldBe 3
            }
        }
    }
    describe("상품권3 상태 변경: 발행 -> 사용가능 -> 사용완료(예외): 유효기간시작일자 전 -> 사용완료(예외): 유효기간시작일자 후") {
        context("발행 -> 사용가능") {
            vouchers[3].statusToUsable(LocalDate.now().plusYears(1))

            it("상태=USABLE") {
                vouchers[3].status shouldBe VoucherStatus.USABLE
            }

            it("usableStartDate=not null, usableEndDate=not null") {
                vouchers[3].usableStartDate shouldNotBe null
                vouchers[3].usableEndDate shouldNotBe null
            }

            it("history size=2") {
                vouchers[3].histories.size shouldBe 2
            }
        }

        context("사용가능 -> 사용완료(예외): 유효기간시작일자 전") {
            val exception = shouldThrow<IllegalStateException> {
                vouchers[3].statusToUsed(LocalDate.now().minusYears(2))
            }

            it("IllegalStateException - 유효기간을 벗어남") {
                exception.message shouldBe "유효기간을 벗어남"
            }

            it("상태=USABLE") {
                vouchers[3].status shouldBe VoucherStatus.USABLE
            }
        }

        context("사용가능 -> 사용완료(예외): 유효기간시작일자 후") {
            val exception = shouldThrow<IllegalStateException> {
                vouchers[3].statusToUsed(LocalDate.now().plusYears(2))
            }

            it("IllegalStateException - 유효기간을 벗어남") {
                exception.message shouldBe "유효기간을 벗어남"
            }

            it("상태=USABLE") {
                vouchers[3].status shouldBe VoucherStatus.USABLE
            }
        }
    }
})
