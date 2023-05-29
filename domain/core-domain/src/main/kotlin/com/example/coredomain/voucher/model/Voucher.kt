package com.example.coredomain.voucher.model

import com.example.coredomain.common.type.VoucherStatus
import com.example.coredomain.contract.model.Contract
import com.example.coredomain.voucherproduct.model.VoucherProduct
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class Voucher(
    val code: String = UUID.randomUUID().toString().uppercase().replace("-", ""),
    var expiredDate: LocalDate? = null,
    val description: String? = null,

    val contract: Contract,
    val product: VoucherProduct,
    val histories: MutableList<VoucherHistory> = mutableListOf(
        VoucherHistory(
            voucherCode = code,
            voucherStatus = VoucherStatus.ISSUED
        )
    ),
) {
    val status: VoucherStatus
        get() {
            expiredDate?.let { date ->
                if (LocalDate.now().isAfter(date)) {
                    return VoucherStatus.EXPIRED
                }
            }

            return histories.last().voucherStatus
        }


    val issuedAt: LocalDateTime
        get() = histories.first { it.voucherStatus == VoucherStatus.ISSUED }.createAt

    fun statusToUsable(expiredDate: LocalDate) {
        if (status != VoucherStatus.ISSUED) {
            throw IllegalStateException("변경 불가능한 상품권 상태")
        }

        histories.add(
            VoucherHistory(
                voucherCode = code,
                voucherStatus = VoucherStatus.USABLE
            )
        )

        this.expiredDate = expiredDate
    }

    fun statusToUnusable() {
        if (!listOf(VoucherStatus.ISSUED, VoucherStatus.USABLE).contains(status)) {
            throw IllegalStateException("변경 불가능한 상품권 상태")
        }

        histories.add(
            VoucherHistory(
                voucherCode = code,
                voucherStatus = VoucherStatus.UNUSABLE
            )
        )
    }

    fun statusToUsed() {
        if (status != VoucherStatus.USABLE) {
            throw IllegalStateException("변경 불가능한 상품권 상태")
        }

        histories.add(
            VoucherHistory(
                voucherCode = code,
                voucherStatus = VoucherStatus.USED
            )
        )
    }
}
