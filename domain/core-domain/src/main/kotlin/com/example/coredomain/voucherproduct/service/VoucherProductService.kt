package com.example.coredomain.voucherproduct.service

import com.example.coredomain.voucherproduct.model.VoucherProduct
import com.example.coredomain.voucherproduct.repository.VoucherProductRepository
import org.springframework.stereotype.Service

@Service
class VoucherProductService(
    private val voucherProductRepository: VoucherProductRepository
) {
    fun findByCode(code: String): VoucherProduct? {
        return voucherProductRepository.findByCode(code)
    }
}
