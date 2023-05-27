package com.example.coredomain.contract.service

import com.example.coredomain.contract.model.Contract
import com.example.coredomain.contract.repository.ContractRepository
import org.springframework.stereotype.Service

@Service
class ContractQueryService(
    private val contractRepository: ContractRepository
) {
    fun findByCode(code: String): Contract? {
        return contractRepository.findByCode(code)
    }
}
