package com.example.coredomain.voucher.service

import com.example.coredomain.voucher.model.Voucher
import com.example.coredomain.voucher.repository.VoucherRepository
import org.springframework.stereotype.Service

@Service
class VoucherService(
    private val voucherRepository: VoucherRepository,
) {
    fun findByCode(code: String): Voucher? {
        return voucherRepository.findByCode(code)
    }
}
