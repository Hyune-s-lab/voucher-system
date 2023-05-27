package com.example.coredomain.voucherproduct.service

import com.example.coredomain.voucherproduct.model.VoucherProduct
import com.example.coredomain.voucherproduct.repository.VoucherProductQueryRepository
import org.springframework.stereotype.Service

@Service
class VoucherProductQueryService(
    private val voucherProductQueryRepository: VoucherProductQueryRepository
) {
    fun findByCode(code: String): VoucherProduct? {
        return voucherProductQueryRepository.findByCode(code)
    }
}
