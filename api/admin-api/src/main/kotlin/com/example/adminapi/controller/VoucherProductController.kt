package com.example.adminapi.controller

import com.example.adminapi.controller.common.VoucherProductResponse
import com.example.coredomain.support.exception.BusinessException
import com.example.coredomain.support.exception.ErrorType
import com.example.coredomain.voucherproduct.service.VoucherProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Tag(name = "상품권종")
@RestController
class VoucherProductController(
    private val voucherProductService: VoucherProductService
) {
    @Operation(summary = "단건 조회")
    @GetMapping("/api/v1/voucher-product/{voucherProductCode}")
    fun findOne(@PathVariable voucherProductCode: String): VoucherProductResponse {
        return voucherProductService.findByCode(voucherProductCode)?.let {
            VoucherProductResponse(it)
        } ?: throw BusinessException(ErrorType.NOT_FOUND_VOUCHER_PRODUCT)
    }
}
