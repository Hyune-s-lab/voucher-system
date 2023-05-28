package com.example.coredomain.contract.model

import com.example.coredomain.voucherproduct.model.VoucherProduct
import java.time.LocalDate

data class Contract(
    val merchantId: String,
    val code: String,
    val name: String,
    val totalAmountLimit: Long,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val description: String? = null,
    val voucherProducts: List<VoucherProduct>,
) {
    fun hasProduct(voucherProduct: VoucherProduct): Boolean {
        return voucherProducts.contains(voucherProduct)
    }

    fun canIssue(now: LocalDate): Boolean {
        return now.isAfter(startDate) && now.isBefore(endDate.plusDays(1))
    }
}
