package com.example.coredomain.support.mock

import com.example.coredomain.contract.model.Contract
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class ContractRepositoryMockAdapter(
    voucherProductRepositoryMockAdapter: VoucherProductRepositoryMockAdapter
) {
    private val map = mutableMapOf(
        "CT0001" to Contract(
            merchantId = "KP0001",
            code = "CT0001",
            name = "KP기업 2023 설 상품권",
            totalAmountLimit = 10_000_000L,
            startDate = LocalDate.now(),
            endDate = LocalDate.now().plusYears(1),
            voucherProducts = listOf(
                voucherProductRepositoryMockAdapter.findByCode("PD0001")!!,
                voucherProductRepositoryMockAdapter.findByCode("PD0003")!!,
            )
        ),
        "CT0002" to Contract(
            merchantId = "KP0001",
            code = "CT0002",
            name = "KP기업 2023 추석 상품권",
            totalAmountLimit = 30_000_000L,
            startDate = LocalDate.now(),
            endDate = LocalDate.now().plusYears(1),
            voucherProducts = listOf(
                voucherProductRepositoryMockAdapter.findByCode("PD0002")!!,
                voucherProductRepositoryMockAdapter.findByCode("PD0003")!!,
            )
        )
    )

    fun findByCode(code: String): Contract? {
        return map[code]
    }
}
