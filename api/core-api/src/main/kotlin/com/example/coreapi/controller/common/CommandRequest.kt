package com.example.coreapi.controller.common

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "command api 필수 요청 값")
interface CommandRequest {
    @get:Schema(title = "요청사 거래고유번호")
    val orderId: String
}
