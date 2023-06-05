package com.example.coredomain.voucher.model

import com.example.coredomain.common.type.VoucherStatus
import java.time.LocalDateTime

data class VoucherHistory(
    val voucherCode: String,
    val voucherStatus: VoucherStatus,
    val createAt: LocalDateTime = LocalDateTime.now(),
)
