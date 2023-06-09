package com.example.coredomain.voucher.repository

import com.example.coredomain.contract.model.Contract
import com.example.coredomain.voucher.model.Voucher
import org.springframework.stereotype.Repository

@Repository
interface VoucherRepository {
    fun findByCode(code: String): Voucher?
    fun findAllByContract(contract: Contract): List<Voucher>

    fun save(voucher: Voucher): Voucher
    fun deleteAll()
}
