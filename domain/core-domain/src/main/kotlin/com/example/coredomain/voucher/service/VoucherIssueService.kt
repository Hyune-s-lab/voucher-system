package com.example.coredomain.voucher.service

import com.example.coredomain.common.type.VoucherStatus
import com.example.coredomain.contract.repository.ContractRepository
import com.example.coredomain.support.exception.BusinessException
import com.example.coredomain.support.exception.ErrorType
import com.example.coredomain.voucher.model.Voucher
import com.example.coredomain.voucher.repository.VoucherRepository
import com.example.coredomain.voucher.service.strategy.VoucherIssueValidationStrategy
import com.example.coredomain.voucherproduct.repository.VoucherProductRepository
import org.springframework.stereotype.Service

@Service
class VoucherIssueService(
    private val validationStrategy: VoucherIssueValidationStrategy,
    private val voucherRepository: VoucherRepository,
    private val contractRepository: ContractRepository,
    private val voucherProductRepository: VoucherProductRepository
) {
    fun issue(contractCode: String, voucherProductCode: String): Voucher {
        val contract = contractRepository.findByCode(contractCode)
            ?: throw BusinessException(ErrorType.NOT_FOUND_CONTRACT)
        val voucherProduct = voucherProductRepository.findByCode(voucherProductCode)
            ?: throw BusinessException(ErrorType.NOT_FOUND_VOUCHER_PRODUCT)
        val totalAmountOfISSUEVoucher = voucherRepository.findAllByContract(contract)
            .filter { it.status == VoucherStatus.ISSUE }
            .sumOf { it.product.price }

        validationStrategy.validate(contract, voucherProduct, totalAmountOfISSUEVoucher)

        return voucherRepository.save(
            Voucher(
                contract = contract,
                product = voucherProduct
            )
        )
    }
}
