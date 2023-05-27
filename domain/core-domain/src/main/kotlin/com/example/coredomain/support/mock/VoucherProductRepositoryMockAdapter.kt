package com.example.coredomain.support.mock

import com.example.coredomain.voucherproduct.model.VoucherProduct
import org.springframework.stereotype.Component

@Component
class VoucherProductRepositoryMockAdapter {
    private val map = mutableMapOf(
        "PD0001" to VoucherProduct(
            code = "PD0001",
            name = "K페이 3만원권",
            price = 30000L
        ),
        "PD0002" to VoucherProduct(
            code = "PD0002",
            name = "K페이 5만원권",
            price = 50000L
        ),
        "PD0003" to VoucherProduct(
            code = "PD0003",
            name = "K페이 10만원권",
            price = 100000L
        ),
    )

    fun findByCode(code: String): VoucherProduct? {
        return map[code]
    }
}
