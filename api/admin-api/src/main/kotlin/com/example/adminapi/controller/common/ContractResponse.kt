package com.example.adminapi.controller.common

import com.example.coredomain.contract.model.Contract
import java.time.LocalDate

data class ContractResponse(
    val merchantId: String,
    val code: String,
    val name: String,
    val totalAmountLimit: Long,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val description: String? = null,
    val voucherProducts: List<VoucherProductResponse>,
) {
    constructor(contract: Contract) : this(
        merchantId = contract.merchantId,
        code = contract.code,
        name = contract.name,
        totalAmountLimit = contract.totalAmountLimit,
        startDate = contract.startDate,
        endDate = contract.endDate,
        description = contract.description,
        voucherProducts = contract.voucherProducts.map { VoucherProductResponse(it) }
    )
}
