package com.example.coreapi.usecase

import com.example.coreapi.controller.common.VoucherProductResponse
import com.example.coredomain.voucherproduct.service.VoucherProductQueryService
import org.springframework.stereotype.Service

@Service
class VoucherProductQueryUsecase(
    private val voucherProductQueryService: VoucherProductQueryService
) {
    fun findByCode(code: String): VoucherProductResponse {
        return voucherProductQueryService.findByCode(code)?.let {
            VoucherProductResponse(it)
        } ?: throw IllegalArgumentException("상품권종이 존재하지 않습니다.")
    }
}
