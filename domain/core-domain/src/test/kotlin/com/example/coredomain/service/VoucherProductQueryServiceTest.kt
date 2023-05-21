package com.example.coredomain.service

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class VoucherProductQueryServiceTest(
    private val voucherProductQueryService: VoucherProductQueryService
) : DescribeSpec({
    describe("상품권종을 조회할 수 있습니다.") {
        context("code == PD0001") {
            val target = voucherProductQueryService.findByCode("PD0001")
            it("exists") {
                target shouldNotBe null
            }
        }
    }
})
