package com.example.coredomain.voucher.service

import com.example.coredomain.contract.repository.ContractRepository
import com.example.coredomain.voucher.model.Voucher
import com.example.coredomain.voucher.repository.VoucherRepository
import com.example.coredomain.voucherproduct.repository.VoucherProductRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class VoucherService(
    private val voucherRepository: VoucherRepository,
    private val contractRepository: ContractRepository,
    private val voucherProductRepository: VoucherProductRepository
) {
    fun findByCode(code: String): Voucher? {
        return voucherRepository.findByCode(code)
    }

    fun issue(contractCode: String, voucherProductCode: String): Voucher {
        val contract = contractRepository.findByCode(contractCode)
            ?: throw IllegalArgumentException("존재하지 않는 계약")

        if (!contract.canIssue(LocalDate.now())) {
            throw IllegalArgumentException("발행가능기간이 유효하지 않은 계약")
        }

        val voucherProduct = voucherProductRepository.findByCode(voucherProductCode)
            ?: throw IllegalArgumentException("존재하지 않는 상품권종")

        if (!contract.hasProduct(voucherProduct)) {
            throw IllegalArgumentException("유효하지 않은 상품권종")
        }

        return voucherRepository.save(
            Voucher(
                contract = contract,
                product = voucherProduct
            )
        )
    }

    fun save(voucher: Voucher): Voucher {
        TODO()
    }
}
