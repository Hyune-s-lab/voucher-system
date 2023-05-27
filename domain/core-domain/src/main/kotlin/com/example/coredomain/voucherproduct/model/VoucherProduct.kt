package com.example.coredomain.voucherproduct.model

data class VoucherProduct(
    val code: String,
    val name: String,
    val price: Long,
    val description: String?,
) {
    constructor(code: String, name: String, price: Long) : this(code, name, price, null)
}
