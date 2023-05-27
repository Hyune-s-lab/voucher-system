package com.example.adminapi.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@Tag(name = "유틸")
@RestController
class UtilController {
    @Operation(summary = "테스트")
    @GetMapping("/test")
    fun test(): LocalDateTime {
        return LocalDateTime.now()
    }
}
