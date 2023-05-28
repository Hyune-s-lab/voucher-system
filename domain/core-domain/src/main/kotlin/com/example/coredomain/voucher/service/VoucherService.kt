package com.example.coredomain.voucher.service

import com.example.coredomain.voucher.model.Voucher
import com.example.coredomain.voucher.repository.VoucherRepository
import com.example.coredomain.voucherproduct.repository.VoucherProductRepository
import org.springframework.stereotype.Service

@Service
class VoucherService(
    private val voucherRepository: VoucherRepository,
    private val voucherProductRepository: VoucherProductRepository
) {
    fun create(contractCode: String, voucherProductCode: String): Voucher {
        TODO()
    }

    fun save(voucher: Voucher): Voucher {
        TODO()
    }
}
