package com.example.coreapi.controller

import com.example.coreapi.controller.common.QueryResponse
import com.example.coreapi.controller.common.VoucherProductResponse
import com.example.coreapi.usecase.VoucherProductQueryUsecase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Tag(name = "상품권종")
@RestController
class VoucherProductController(
    private val voucherProductQueryUsecase: VoucherProductQueryUsecase
) {
    @Operation(summary = "단건 조회")
    @GetMapping("/api/v1/voucher-product/{voucherProductCode}}")
    fun findOne(@PathVariable voucherProductCode: String): VoucherProductFindOneResponse {
        return VoucherProductFindOneResponse(
            voucherProduct = voucherProductQueryUsecase.findByCode(voucherProductCode)
        )
    }
}

data class VoucherProductFindOneResponse(
    @Schema(title = "상품권종")
    val voucherProduct: VoucherProductResponse,
) : QueryResponse
