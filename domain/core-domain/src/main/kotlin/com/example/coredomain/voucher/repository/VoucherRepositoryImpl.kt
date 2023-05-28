package com.example.coredomain.voucher.repository

import com.example.coredomain.support.mock.VoucherRepositoryMockAdapter
import com.example.coredomain.voucher.model.Voucher
import org.springframework.stereotype.Repository

@Repository
class VoucherRepositoryImpl(
    private val voucherRepositoryMockAdapter: VoucherRepositoryMockAdapter
) : VoucherRepository {
    override fun findByCode(code: String): Voucher? {
        return voucherRepositoryMockAdapter.findByCode(code)
    }

    override fun save(voucher: Voucher): Voucher {
        return voucherRepositoryMockAdapter.save(voucher)
    }
}
