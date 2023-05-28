package com.example.coredomain.voucherproduct.repository

import com.example.coredomain.support.mock.VoucherProductRepositoryMockAdapter
import com.example.coredomain.voucherproduct.model.VoucherProduct
import org.springframework.stereotype.Repository

@Repository
class VoucherProductRepositoryImpl(
    private val voucherProductRepositoryMockAdapter: VoucherProductRepositoryMockAdapter
) : VoucherProductRepository {
    override fun findByCode(code: String): VoucherProduct? {
        return voucherProductRepositoryMockAdapter.findByCode(code)
    }

    override fun save(voucherProduct: VoucherProduct): VoucherProduct {
        return voucherProductRepositoryMockAdapter.save(voucherProduct)
    }
}
