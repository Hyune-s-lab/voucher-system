package com.example.coredomain.contract.repository

import com.example.coredomain.contract.model.Contract
import com.example.coredomain.support.mock.ContractRepositoryMockAdapter
import org.springframework.stereotype.Repository

@Repository
class ContractRepositoryImpl(
    private val contractRepositoryMockAdapter: ContractRepositoryMockAdapter
) : ContractRepository {
    override fun findByCode(code: String): Contract? {
        return contractRepositoryMockAdapter.findByCode(code)
    }

    override fun save(contract: Contract): Contract {
        return contractRepositoryMockAdapter.save(contract)
    }
}
