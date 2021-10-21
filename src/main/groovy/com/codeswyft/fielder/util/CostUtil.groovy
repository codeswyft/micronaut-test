package com.codeswyft.fielder.util

import com.codeswyft.fielder.domain.CostEO
import com.codeswyft.fielder.domain.DiscountEO
import com.codeswyft.fielder.domain.TaxRateGroupPO
import com.codeswyft.fielder.domain.TransactionLineItemPO
import groovy.transform.CompileStatic

@CompileStatic
class CostUtil {

    static CostEO calculateCost(Set<? extends TransactionLineItemPO> lineItems, DiscountEO discount) {
        BigDecimal subTotal = BigDecimal.ZERO
        BigDecimal totalDiscount = BigDecimal.ZERO
        BigDecimal totalTax = BigDecimal.ZERO
        DiscountEO normalizedDiscount = DiscountUtil.normalizeDiscount(lineItems, discount)

        lineItems?.each { TransactionLineItemPO lineItem ->
            if (lineItem.quantity > 0) {
                TaxRateGroupPO taxRateGroup = lineItem.taxRateGroup
                BigDecimal totalTaxRate = taxRateGroup?.totalTaxRate ?: 0.0
                // note: we are using the rounded value of quantity * unitPrice (we want rounded)
                BigDecimal rawCost = lineItem.total
                BigDecimal discountedCost = DiscountUtil.applyDiscount(rawCost, normalizedDiscount)
                subTotal += rawCost // tracking sub total without discount
                totalDiscount += (rawCost - discountedCost) // tracking total discount
                totalTax += (discountedCost * totalTaxRate) // tracking total tax
            }
        }

        return new CostEO(
            subTotal: subTotal.round(2),
            totalTax: totalTax.round(2),
            totalDiscount: totalDiscount.round(2),
            total: (subTotal - totalDiscount + totalTax).round(2)
        )
    }


}
