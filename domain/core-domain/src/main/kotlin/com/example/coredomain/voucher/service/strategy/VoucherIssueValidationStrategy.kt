package com.example.coredomain.voucher.service.strategy

import com.example.coredomain.contract.model.Contract
import com.example.coredomain.support.exception.BusinessException
import com.example.coredomain.support.exception.ErrorType
import com.example.coredomain.voucherproduct.model.VoucherProduct
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class VoucherIssueValidationStrategy {
    fun validate(contract: Contract, voucherProduct: VoucherProduct, totalAmountOfIssuedVoucher: Long) {
        계약기간(contract)
        유효상품권종(contract, voucherProduct)
        상품권_발행한도(contract, voucherProduct, totalAmountOfIssuedVoucher)
    }

    private fun 상품권_발행한도(contract: Contract, voucherProduct: VoucherProduct, totalAmountOfIssuedVoucher: Long) {
        if (totalAmountOfIssuedVoucher + voucherProduct.price > contract.totalAmountLimit) {
            throw BusinessException(ErrorType.INVALID_TOTAL_AMOUNT_LIMIT_OF_CONTRACT)
        }
    }

    private fun 유효상품권종(contract: Contract, voucherProduct: VoucherProduct) {
        if (contract.hasProduct(voucherProduct)) return

        throw BusinessException(ErrorType.INVALID_VOUCHER_PRODUCT)
    }

    private fun 계약기간(contract: Contract) {
        if (contract.isIssuableDate(LocalDate.now())) return

        throw BusinessException(ErrorType.INVALID_VALIDITY_PERIOD_OF_CONTRACT)
    }
}
