package com.example.coredomain.voucher.model

import com.example.coredomain.common.type.VoucherStatus
import com.example.coredomain.contract.model.Contract
import com.example.coredomain.support.exception.BusinessException
import com.example.coredomain.support.exception.ErrorType
import com.example.coredomain.voucherproduct.model.VoucherProduct
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class Voucher(
    val code: String = UUID.randomUUID().toString().uppercase().replace("-", ""),
    var validityEndDate: LocalDate? = null,
    val description: String? = null,

    val contract: Contract,
    val product: VoucherProduct,
    val histories: MutableList<VoucherHistory> = mutableListOf(
        VoucherHistory(
            voucherCode = code,
            voucherStatus = VoucherStatus.ISSUE
        )
    ),
) {
    val status: VoucherStatus
        get() {
            validityEndDate?.let { date ->
                if (LocalDate.now().isAfter(date)) {
                    return VoucherStatus.EXPIRE
                }
            }

            return histories.last().voucherStatus
        }

    val issueAt: LocalDateTime
        get() = histories.first { it.voucherStatus == VoucherStatus.ISSUE }.createAt

    val validityStartDate: LocalDate?
        get() = histories.firstOrNull { it.voucherStatus == VoucherStatus.USABLE }?.createAt?.toLocalDate()

    fun statusToUsable(usableEndDate: LocalDate) {
        if (status != VoucherStatus.ISSUE) {
            throw BusinessException(ErrorType.INVALID_VOUCHER_STATUS_TO_USABLE)
        }

        histories.add(
            VoucherHistory(
                voucherCode = code,
                voucherStatus = VoucherStatus.USABLE
            )
        )

        this.validityEndDate = usableEndDate
    }

    fun statusToUnusable(voucherStatus: VoucherStatus) {
        if (!VoucherStatus.POSSIBLE_TO_UNUSABLE.contains(status)) {
            throw BusinessException(ErrorType.INVALID_VOUCHER_STATUS_TO_UNUSABLE)
        }

        if (!VoucherStatus.UNUSABLES.contains(voucherStatus)) {
            throw BusinessException(ErrorType.INVALID_VOUCHER_STATUS_TO_UNUSABLE)
        }

        histories.add(
            VoucherHistory(
                voucherCode = code,
                voucherStatus = voucherStatus
            )
        )
    }

    fun statusToUsed(usedDate: LocalDate = LocalDate.now()) {
        if (status != VoucherStatus.USABLE) {
            throw BusinessException(ErrorType.INVALID_VOUCHER_STATUS_TO_USED)
        }

        if (usedDate.isBefore(validityStartDate) || usedDate.isAfter(validityEndDate)) {
            throw BusinessException(ErrorType.INVALID_VALIDITY_PERIOD_OF_VOUCHER)
        }

        histories.add(
            VoucherHistory(
                voucherCode = code,
                voucherStatus = VoucherStatus.USED
            )
        )
    }
}
