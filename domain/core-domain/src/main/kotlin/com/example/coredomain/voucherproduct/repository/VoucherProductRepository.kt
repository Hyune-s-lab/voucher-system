package com.example.coredomain.voucherproduct.repository

import com.example.coredomain.voucherproduct.model.VoucherProduct

interface VoucherProductRepository {
    fun findByCode(code: String): VoucherProduct?
}
