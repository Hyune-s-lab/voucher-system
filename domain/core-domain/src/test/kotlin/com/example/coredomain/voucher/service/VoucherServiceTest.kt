package com.example.coredomain.voucher.service

import com.example.coredomain.contract.repository.ContractRepository
import com.example.coredomain.voucherproduct.repository.VoucherProductRepository
import io.kotest.core.spec.style.DescribeSpec
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class VoucherServiceTest(
    private val voucherService: VoucherService,
    private val contractRepository: ContractRepository,
    private val voucherProductRepository: VoucherProductRepository
) : DescribeSpec({
    describe("상품권을 발핼합니다.") {
        context("존재하는 계약코드와 상품권종코드를 입력받아 발행합니다.") {
            it("발행 성공") {

            }
        }

        context("계약기간이 초과한 계약코드를 입력받아 발행합니다.") {
            it("IllegalArgumentException - 유효하지 않은 계약코드") {

            }
        }

        context("존재하지 않는 계약코드를 입력받아 발행합니다.") {
            it("IllegalArgumentException - 유효하지 않은 계약코드") {

            }
        }

        context("존재하는 계약코드이지만 상품권종 목록에 없는 상품권종코드를 입력받아 발행합니다.") {
            it("IllegalArgumentException - 유효하지 않은 상품권종코드") {

            }
        }
    }
})
