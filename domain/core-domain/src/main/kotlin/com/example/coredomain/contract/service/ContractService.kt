package com.example.coredomain.contract.service

import com.example.coredomain.contract.model.Contract
import com.example.coredomain.contract.repository.ContractRepository
import org.springframework.stereotype.Service

@Service
class ContractService(
    private val contractRepository: ContractRepository
) {
    fun findByCode(code: String): Contract? {
        return contractRepository.findByCode(code)
    }

    fun create(contract: Contract): Contract {
        return contractRepository.save(contract)
    }
}
