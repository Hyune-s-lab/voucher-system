package com.example.adminapi.controller.common

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "command api 필수 응답 값")
interface CommandResponse {
    @get:Schema(title = "거래고유번호")
    val tid: String
}
