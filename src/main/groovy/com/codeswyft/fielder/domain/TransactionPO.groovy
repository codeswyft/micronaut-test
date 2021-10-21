package com.codeswyft.fielder.domain

import com.codeswyft.fielder.model.TaxRate
import com.codeswyft.fielder.model.TaxRateAmount
import com.codeswyft.fielder.model.TaxSummary
import com.codeswyft.fielder.util.CostUtil
import com.codeswyft.fielder.util.DiscountUtil
import groovy.transform.CompileStatic
import io.micronaut.core.annotation.NonNull

import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.MappedSuperclass
import javax.persistence.Transient

@CompileStatic
@MappedSuperclass
abstract class TransactionPO<T extends TransactionLineItemPO> extends BasePO {

    @Column(name = 'organization_id', nullable = false, updatable = false)
    String organizationId

    @Column(name = 'number', nullable = false, updatable = false)
    @NonNull
    Integer number

    @NonNull
    @Embedded
    DiscountEO discount = new DiscountEO(value: BigDecimal.ZERO)

    void setDiscount(DiscountEO discountEO) {
        this.@discount = discountEO ?: new DiscountEO(value: BigDecimal.ZERO)
        recalculateCost()
    }

    @Column(name = 'notes', nullable = true, columnDefinition = 'TEXT')
    String notes

    // The 3-character international currency code (https://www.science.co.il/international/Currency-codes.php )
    @Column(name = 'currency_code', nullable = false, updatable = false, length = 5)
    String currencyCode

    @NonNull
    @Embedded
    CostEO cost = new CostEO(total: BigDecimal.ZERO)

    @Transient
    TaxSummary getTaxSummary() {
        TaxSummary taxSummary = new TaxSummary()
        taxSummary.total = this.@cost?.totalTax
        DiscountEO normalizedDiscount = DiscountUtil.normalizeDiscount(this.getLineItems(), discount)
        List<TaxRateAmount> taxRateAmounts = []
        getLineItems()?.each { T lineItem ->
            lineItem.taxRateGroup?.taxRates?.findAll { TaxRatePO taxRatePO -> taxRatePO.rate > 0 }?.each { TaxRatePO taxRatePO ->
                TaxRateAmount taxRateAmount = taxRateAmounts.find { it.taxRate?.id == taxRatePO.id }
                if (!taxRateAmount) {
                    TaxRate taxRate = new TaxRate(id: taxRatePO.id, name: taxRatePO.name, rate: taxRatePO.rate)
                    taxRateAmount = new TaxRateAmount(taxRate: taxRate, amount: 0.0)
                    taxRateAmounts.add(taxRateAmount)
                }
                BigDecimal discountedLineTotal = DiscountUtil.applyDiscount(lineItem.total, normalizedDiscount)
                taxRateAmount.amount += (discountedLineTotal * taxRatePO.rate)
            }
        }

        taxRateAmounts.each { it.amount = it.amount.round(2) }

        // If necessary, adjust the largest TaxRateAmount so that the sum of all of them matches the totalTaxRate
        BigDecimal taxRateAmountSum = taxRateAmounts.sum(0, { it.amount }) as BigDecimal
        BigDecimal error = taxRateAmountSum - this.@cost?.totalTax
        if (error != 0) {
            TaxRateAmount largest = taxRateAmounts.sort { it.amount }[-1]
            largest.amount = (largest.amount - error).round(2)
        }

        taxSummary.taxRateAmounts = taxRateAmounts
        return taxSummary
    }

    // we never want to explicitly set the cost (it is derived)
    void setCost(CostEO costEO) {
        // do nothing...
    }

    protected void recalculateCost() {
        this.@cost = CostUtil.calculateCost(this.lineItems, discount)
    }

    abstract Set<T> getLineItems()

    abstract void setLineItems(Set<T> lineItems)

}
