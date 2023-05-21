package com.example.coredomain.service

import com.example.coredomain.model.VoucherProduct
import com.example.coredomain.repository.VoucherProductQueryRepository
import org.springframework.stereotype.Service

@Service
class VoucherProductQueryService(
    private val voucherProductQueryRepository: VoucherProductQueryRepository
) {
    fun findByCode(code: String): VoucherProduct? {
        return voucherProductQueryRepository.findByCode(code)
    }
}
