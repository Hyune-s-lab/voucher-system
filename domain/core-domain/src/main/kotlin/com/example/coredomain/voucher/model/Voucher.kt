package com.example.coredomain.voucher.model

import com.example.coredomain.voucherproduct.model.VoucherProduct
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class Voucher(
    val code: String = UUID.randomUUID().toString().uppercase().replace("-", ""),
    val issuedAt: LocalDateTime = LocalDateTime.now(),
    val expiredDate: LocalDate = LocalDate.now().plusYears(5),
    val description: String? = null,

    val voucherProduct: VoucherProduct,
)
