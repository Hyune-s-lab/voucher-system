package com.example.coreapi.controller

import com.example.coreapi.controller.common.CommandResponse
import com.example.coredomain.voucher.service.VoucherService
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@Tag(name = "상품권")
@RestController
class VoucherController(
    private val voucherService: VoucherService
) {
    @Operation(summary = "발행")
    @GetMapping("/api/v1/voucher/publish")
    fun publish(): VoucherPublishResponse {
        return voucherService.create().let {
            VoucherPublishResponse(
                voucherCode = it.code,
                issuedAt = it.issuedAt
            )
        }
    }
}


@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class VoucherPublishResponse(
    override val tid: String? = null,

    @Schema(title = "상품권 코드")
    val voucherCode: String,

    @Schema(title = "발행 일시")
    val issuedAt: LocalDateTime
) : CommandResponse
