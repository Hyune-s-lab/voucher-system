package com.example.coredomain.voucherproduct.repository

import com.example.coredomain.support.mock.VoucherProductRepositoryMockAdapter
import com.example.coredomain.voucherproduct.model.VoucherProduct
import org.springframework.stereotype.Repository

@Repository
class VoucherProductQueryRepositoryImpl(
    private val voucherProductRepositoryMockAdapter: VoucherProductRepositoryMockAdapter
) : VoucherProductQueryRepository {
    override fun findByCode(code: String): VoucherProduct? {
        return voucherProductRepositoryMockAdapter.findByCode(code)
    }
}
