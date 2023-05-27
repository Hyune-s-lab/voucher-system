package com.example.coredomain.voucherproduct.repository

import com.example.coredomain.voucherproduct.model.VoucherProduct

interface VoucherProductQueryRepository {
    fun findByCode(code: String): VoucherProduct?
}
