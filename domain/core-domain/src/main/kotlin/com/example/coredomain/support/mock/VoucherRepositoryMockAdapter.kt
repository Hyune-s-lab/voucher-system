package com.example.coredomain.support.mock

import com.example.coredomain.contract.model.Contract
import com.example.coredomain.voucher.model.Voucher
import org.springframework.stereotype.Component

@Component
class VoucherRepositoryMockAdapter {
    private val map = mutableMapOf<String, Voucher>()

    fun findByCode(code: String): Voucher? {
        return map[code]
    }

    fun findAllByContract(contract: Contract): List<Voucher> {
        return map.values.filter { it.contract == contract }
    }

    fun save(voucher: Voucher): Voucher {
        map[voucher.code] = voucher
        return voucher
    }

    fun deleteAll() {
        map.clear()
    }
}
