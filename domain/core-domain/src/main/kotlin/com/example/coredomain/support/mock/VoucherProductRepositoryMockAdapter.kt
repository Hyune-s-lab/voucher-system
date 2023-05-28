package com.example.coredomain.support.mock

import com.example.coredomain.voucherproduct.model.VoucherProduct
import org.springframework.stereotype.Component

@Component
class VoucherProductRepositoryMockAdapter {
    private val map = mutableMapOf<String, VoucherProduct>()

    fun findByCode(code: String): VoucherProduct? {
        return map[code]
    }

    fun save(voucherProduct: VoucherProduct): VoucherProduct {
        map[voucherProduct.code] = voucherProduct
        return voucherProduct
    }

    fun deleteAll() {
        map.clear()
    }
}
