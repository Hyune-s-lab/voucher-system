package com.example.coredomain.voucherproduct.service

import com.example.coredomain.contract.repository.ContractRepository
import com.example.coredomain.testutil.TestFixture
import com.example.coredomain.voucherproduct.repository.VoucherProductRepository
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class VoucherProductServiceTest(
    private val voucherProductService: VoucherProductService,
    private val contractRepository: ContractRepository,
    private val voucherProductRepository: VoucherProductRepository
) : DescribeSpec({
    describe("상품권종을 조회할 수 있습니다.") {
        context("code == PD0001") {
            val target = voucherProductService.findByCode("PD0001")
            it("exists") {
                target shouldNotBe null
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
