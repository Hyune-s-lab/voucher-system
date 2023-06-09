package com.example.coredomain.testutil

import com.example.coredomain.contract.model.Contract
import com.example.coredomain.voucherproduct.model.VoucherProduct
import java.time.LocalDate

object TestFixture {
    val 상품권종 = listOf(
        VoucherProduct(
            code = "PD0001",
            name = "K페이 3만원권",
            price = 30000L
        ),
        VoucherProduct(
            code = "PD0002",
            name = "K페이 5만원권",
            price = 50000L
        ),
        VoucherProduct(
            code = "PD0003",
            name = "K페이 10만원권",
            price = 100000L
        ),
    )

    val 계약_유효기간_지금보다_이전 = listOf(
        Contract(
            merchantId = "KP0001",
            code = "CT0001",
            name = "KP기업 발행 가능 계약 1",
            totalAmountLimit = 10_000_000L,
            startDate = LocalDate.now().minusYears(3),
            endDate = LocalDate.now().minusYears(1),
            voucherProducts = listOf(
                상품권종[0],
                상품권종[2],
            )
        )
    )

    val 계약_유효기간유효 = listOf(
        Contract(
            merchantId = "KP0001",
            code = "CT0002",
            name = "KP기업 발행 가능 계약 1",
            totalAmountLimit = 10_000_000L,
            startDate = LocalDate.now().minusYears(1),
            endDate = LocalDate.now().plusYears(1),
            voucherProducts = listOf(
                상품권종[0],
                상품권종[2],
            )
        )
    )

    val 계약_유효기간_지금보다_이후 = listOf(
        Contract(
            merchantId = "KP0001",
            code = "CT0003",
            name = "KP기업 만료된 계약 1",
            totalAmountLimit = 30_000_000L,
            startDate = LocalDate.now().plusYears(1),
            endDate = LocalDate.now().plusYears(3),
            description = "만료된 계약",
            voucherProducts = listOf(
                상품권종[0],
                상품권종[2],
            )
        )
    )

    val 계약_총계약금액_50만원 = listOf(
        Contract(
            merchantId = "KP0001",
            code = "CT0004",
            name = "KP기업 총계약금액 50만원 계약 1",
            totalAmountLimit = 500_000L,
            startDate = LocalDate.now().minusYears(1),
            endDate = LocalDate.now().plusYears(1),
            description = "총계약금액 50만원",
            voucherProducts = listOf(
                상품권종[0],
                상품권종[2],
            )
        )
    )

    val 계약_전체 = 계약_유효기간_지금보다_이전 + 계약_유효기간유효 + 계약_유효기간_지금보다_이후 + 계약_총계약금액_50만원
}
