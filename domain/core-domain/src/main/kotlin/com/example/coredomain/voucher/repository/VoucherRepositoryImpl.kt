package com.example.coredomain.voucher.repository

import com.example.coredomain.contract.model.Contract
import com.example.coredomain.support.mock.VoucherRepositoryMockAdapter
import com.example.coredomain.voucher.model.Voucher
import org.springframework.stereotype.Repository

@Repository
class VoucherRepositoryImpl(
    private val mockAdapter: VoucherRepositoryMockAdapter
) : VoucherRepository {
    override fun findByCode(code: String): Voucher? {
        return mockAdapter.findByCode(code)
    }

    override fun findAllByContract(contract: Contract): List<Voucher> {
        return mockAdapter.findAllByContract(contract)
    }

    override fun save(voucher: Voucher): Voucher {
        return mockAdapter.save(voucher)
    }

    override fun deleteAll() {
        mockAdapter.deleteAll()
    }
}
