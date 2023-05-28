package com.example.coredomain.common.type

enum class VoucherStatus(description: String) {
    ISSUED("발행"),
    USABLE("사용가능"),
    UNUSABLE("사용불가"),
    USED("사용완료"),
    EXPIRED("만료"),
}
