package com.example.coredomain.common.type

enum class VoucherStatus(description: String) {
    ISSUE("발행"),
    USABLE("사용가능"),
    UNUSABLE_NORMAL("사용불가_일반"),
    UNUSABLE_ADMIN("사용불가_운영"),
    UNUSABLE_NETWORK("사용불가_망취소"),
    USED("사용완료"),
    EXPIRE("만료"),
    ;

    companion object {
        val POSSIBLE_TO_UNUSABLE = listOf(ISSUE, USABLE)
        val UNUSABLES = listOf(UNUSABLE_NORMAL, UNUSABLE_ADMIN, UNUSABLE_NETWORK)
    }
}
