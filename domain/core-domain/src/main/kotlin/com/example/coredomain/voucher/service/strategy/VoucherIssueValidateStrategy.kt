package com.example.coredomain.voucher.service.strategy

import com.example.coredomain.contract.model.Contract
import com.example.coredomain.voucher.repository.VoucherRepository
import com.example.coredomain.voucherproduct.model.VoucherProduct
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class VoucherIssueValidateStrategy(
    private val voucherRepository: VoucherRepository,
) {
    fun validate(contract: Contract, voucherProduct: VoucherProduct) {
        계약기간(contract)
        유효상품권종(contract, voucherProduct)
        상품권_발행한도(contract, voucherProduct)
    }

    private fun 상품권_발행한도(contract: Contract, voucherProduct: VoucherProduct) {
        val currentAmountSum = voucherRepository.findAllByContract(contract)
            .sumOf { it.product.price }

        if (contract.totalAmountLimit < currentAmountSum + voucherProduct.price) {
            throw IllegalArgumentException("상품권 발행한도 초과")
        }
    }

    private fun 유효상품권종(contract: Contract, voucherProduct: VoucherProduct) {
        if (!contract.hasProduct(voucherProduct)) {
            throw IllegalArgumentException("유효하지 않은 상품권종")
        }
    }

    private fun 계약기간(contract: Contract) {
        if (!contract.canIssue(LocalDate.now())) {
            throw IllegalArgumentException("유효하지 않은 계약기간")
        }
    }
}
