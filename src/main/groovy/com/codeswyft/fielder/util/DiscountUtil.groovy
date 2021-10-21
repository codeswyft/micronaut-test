package com.codeswyft.fielder.util

import com.codeswyft.fielder.domain.DiscountEO
import com.codeswyft.fielder.domain.TransactionLineItemPO
import com.codeswyft.fielder.model.Discount
import com.codeswyft.fielder.model.DiscountType
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

@Slf4j
@CompileStatic
class DiscountUtil {

    //===========================================================================================
    // Never round in this class (that will only be done when/if the callers want to do so)
    //===========================================================================================

    // convert discount to type percentage since we are defining the discount at the TransactionPO level
    static DiscountEO normalizeDiscount(Set<? extends TransactionLineItemPO> lineItems, DiscountEO discount) {
        if (!discount || discount.value == BigDecimal.ZERO || !lineItems) {
            return new DiscountEO(value: BigDecimal.ZERO, type: DiscountType.AMOUNT)
        } else if (discount.type == DiscountType.PERCENTAGE) {
            return discount
        } else {
            BigDecimal discountValue = 0.0
            BigDecimal totalCostWithoutDiscount = lineItems?.sum { TransactionLineItemPO lineItemPO ->
                (lineItemPO.unitPrice * lineItemPO.quantity.toBigDecimal())
            } as BigDecimal
            if (totalCostWithoutDiscount > 0.0) {
                BigDecimal totalCostWithDiscount = (totalCostWithoutDiscount - discount.value)
                discountValue = new BigDecimal('1.0000') - (totalCostWithDiscount / totalCostWithoutDiscount)
            }
            return new DiscountEO(value: discountValue, type: DiscountType.PERCENTAGE)
        }
    }

    static BigDecimal applyDiscount(BigDecimal amount, DiscountEO discountEO) {
        return applyDiscount(amount, discountEO.type, discountEO.value)
    }

    static BigDecimal applyDiscount(BigDecimal amount, Discount discount) {
        return applyDiscount(amount, discount.type, discount.value)
    }

    static BigDecimal applyDiscount(BigDecimal amount, DiscountType type, BigDecimal value) {
        BigDecimal discount = BigDecimal.ZERO

        if (type == DiscountType.AMOUNT) {
            if (value > amount) {
                log.warn("discounted value (\${}) is greater than actual amount (\${})!", value, amount)
            }
            discount = value
        } else if (type == DiscountType.PERCENTAGE) {
            if (value >= BigDecimal.ZERO && value <= BigDecimal.ONE) {
                discount = amount * value
            } else {
                throw new IllegalArgumentException("discount percentage must be in the range [0, 1]!")
            }
        }

        // cannot go negative
        if (discount > amount) {
            discount = amount
        }

        return amount.subtract(discount)
    }
}
