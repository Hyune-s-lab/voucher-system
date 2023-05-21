package com.example.coredomain.repository

import com.example.coredomain.model.VoucherProduct

interface VoucherProductQueryRepository {
    fun findByCode(code: String): VoucherProduct?
}
