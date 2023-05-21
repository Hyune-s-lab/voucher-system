package com.example.coredomain.repository

import com.example.coredomain.model.VoucherProduct
import com.example.coredomain.support.mock.VoucherProductRepositoryMockAdapter
import org.springframework.stereotype.Repository

@Repository
class VoucherProductQueryRepositoryImpl(
    private val voucherProductRepositoryMockAdapter: VoucherProductRepositoryMockAdapter
) : VoucherProductQueryRepository {
    override fun findByCode(code: String): VoucherProduct? {
        return voucherProductRepositoryMockAdapter.findByCode(code)
    }
}
