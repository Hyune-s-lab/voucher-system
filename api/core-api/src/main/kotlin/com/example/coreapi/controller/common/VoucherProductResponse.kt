package com.example.coreapi.controller.common

import com.example.coredomain.model.VoucherProduct
import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "상품권종 응답")
data class VoucherProductResponse(
    val code: String,
    val name: String,
    val price: Long,
    val description: String?,
) {
    constructor(voucherProduct: VoucherProduct) : this(
        code = voucherProduct.code,
        name = voucherProduct.name,
        price = voucherProduct.price,
        description = voucherProduct.description,
    )
}
