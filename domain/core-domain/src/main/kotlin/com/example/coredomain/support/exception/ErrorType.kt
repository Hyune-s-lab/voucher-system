package com.example.coredomain.support.exception

enum class ErrorType(val message: String) {
    // 상품권
    INVALID_VOUCHER_STATUS_TO_USABLE("사용가능으로 변경할 수 없는 상품권 상태"),
    INVALID_VOUCHER_STATUS_TO_UNUSABLE("사용불가로 변경할 수 없는 상품권 상태"),
    INVALID_VOUCHER_STATUS_TO_USED("사용완료로 변경할 수 없는 상품권 상태"),
    INVALID_VALIDITY_PERIOD_OF_VOUCHER("유효기간을 벗어난 상품권"),

    // 상품권종
    INVALID_VOUCHER_PRODUCT("유효하지 않은 상품권종"),
    NOT_FOUND_VOUCHER_PRODUCT("존재하지 않는 상품권종"),

    // 계약
    NOT_FOUND_CONTRACT("존재하지 않는 계약"),
    INVALID_VALIDITY_PERIOD_OF_CONTRACT("유효하지 않은 계약기간"),
    INVALID_TOTAL_AMOUNT_LIMIT_OF_CONTRACT("상품권 총계약금액 초과"),
}
