package com.example.coredomain.contract.repository

import com.example.coredomain.contract.model.Contract

interface ContractRepository {
    fun findByCode(code: String): Contract?
}
