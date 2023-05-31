package com.example.coredomain.voucher.service

import com.example.coredomain.common.type.VoucherStatus
import com.example.coredomain.contract.repository.ContractRepository
import com.example.coredomain.voucher.model.Voucher
import com.example.coredomain.voucher.repository.VoucherRepository
import com.example.coredomain.voucher.service.strategy.VoucherIssueValidateStrategy
import com.example.coredomain.voucherproduct.repository.VoucherProductRepository
import org.springframework.stereotype.Service

@Service
class VoucherCreateService(
    private val voucherIssueValidateStrategy: VoucherIssueValidateStrategy,
    private val voucherRepository: VoucherRepository,
    private val contractRepository: ContractRepository,
    private val voucherProductRepository: VoucherProductRepository
) {
    fun issue(contractCode: String, voucherProductCode: String): Voucher {
        val contract = contractRepository.findByCode(contractCode)
            ?: throw IllegalArgumentException("존재하지 않는 계약")
        val voucherProduct = voucherProductRepository.findByCode(voucherProductCode)
            ?: throw IllegalArgumentException("존재하지 않는 상품권종")
        val totalAmountOfIssuedVoucher = voucherRepository.findAllByContract(contract)
            .filter { it.status == VoucherStatus.ISSUED }
            .sumOf { it.product.price }

        voucherIssueValidateStrategy.validate(contract, voucherProduct, totalAmountOfIssuedVoucher)

        return voucherRepository.save(
            Voucher(
                contract = contract,
                product = voucherProduct
            )
        )
    }
}
