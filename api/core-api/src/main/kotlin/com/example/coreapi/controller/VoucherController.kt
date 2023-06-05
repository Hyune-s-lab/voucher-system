package com.example.coreapi.controller

import com.example.coreapi.controller.common.CommandRequest
import com.example.coreapi.controller.common.CommandResponse
import com.example.coredomain.voucher.service.VoucherService
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@Tag(name = "상품권")
@RestController
class VoucherController(
    private val voucherService: VoucherService
) {
    @Operation(summary = "발행")
    @PostMapping("/api/v1/voucher/issue")
    fun issue(request: VoucherIssueRequest): VoucherIssueResponse {
        return voucherService.issue(request.contractCode, request.voucherProductCode).let {
            VoucherIssueResponse(
                voucherCode = it.code,
                issuedAt = it.issuedAt
            )
        }
    }
}


data class VoucherIssueRequest(
    override val orderId: String,

    @Schema(title = "계약코드")
    val contractCode: String,

    @Schema(title = "상품권종코드")
    val voucherProductCode: String
) : CommandRequest


@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class VoucherIssueResponse(
    override val tid: String? = null,

    @Schema(title = "상품권코드")
    val voucherCode: String,

    @Schema(title = "발행일시")
    val issuedAt: LocalDateTime
) : CommandResponse
