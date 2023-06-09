package com.example.coredomain.contract.service

import com.example.coredomain.contract.repository.ContractRepository
import com.example.coredomain.testutil.TestFixture
import com.example.coredomain.voucherproduct.repository.VoucherProductRepository
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ContractServiceTest(
    private val contractService: ContractService,
    private val contractRepository: ContractRepository,
    private val voucherProductRepository: VoucherProductRepository
) : DescribeSpec({
    describe("계약을 조회할 수 있습니다.") {
        context("code == CT0001") {
            val target = contractService.findByCode("CT0001")

            it("exists") {
                target shouldNotBe null
            }

            it("상품권종이 1개 이상 있습니다.") {
                target!!.voucherProducts.size shouldBeGreaterThanOrEqual 1
            }
        }
    }
}) {
    init {
        TestFixture.상품권종.forEach { voucherProductRepository.save(it) }
        TestFixture.계약_전체.forEach { contractRepository.save(it) }
    }

    override suspend fun afterSpec(spec: Spec) {
        super.afterSpec(spec)
        contractRepository.deleteAll()
        voucherProductRepository.deleteAll()
    }
}
