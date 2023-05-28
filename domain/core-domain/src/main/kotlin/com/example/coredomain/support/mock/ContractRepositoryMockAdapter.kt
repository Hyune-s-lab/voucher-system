package com.example.coredomain.support.mock

import com.example.coredomain.contract.model.Contract
import org.springframework.stereotype.Component

@Component
class ContractRepositoryMockAdapter {
    private val map = mutableMapOf<String, Contract>()

    fun findByCode(code: String): Contract? {
        return map[code]
    }

    fun save(contract: Contract): Contract {
        map[contract.code] = contract
        return contract
    }

    fun deleteAll() {
        map.clear()
    }
}
