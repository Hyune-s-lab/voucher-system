package com.example.coredomain.voucherproduct.service

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class VoucherProductServiceTest(
    private val voucherProductService: VoucherProductService
) : DescribeSpec({
    describe("상품권종을 조회할 수 있습니다.") {
        context("code == PD0001") {
            val target = voucherProductService.findByCode("PD0001")
            it("exists") {
                target shouldNotBe null
            }
        }
    }
})
