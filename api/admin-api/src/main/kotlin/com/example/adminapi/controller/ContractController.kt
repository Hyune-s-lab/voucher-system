package com.example.adminapi.controller

import com.example.adminapi.controller.common.ContractResponse
import com.example.coredomain.contract.service.ContractService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Tag(name = "계약")
@RestController
class ContractController(
    private val contractService: ContractService
) {
    @Operation(summary = "단건 조회")
    @GetMapping("/api/v1/contract/{contractCode}}")
    fun findOne(@PathVariable contractCode: String): ContractResponse {
        return contractService.findByCode(contractCode)?.let {
            ContractResponse(it)
        } ?: throw IllegalArgumentException("존재하지 않는 계약 입니다.")
    }
}

