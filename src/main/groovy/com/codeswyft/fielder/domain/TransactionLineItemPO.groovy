package com.codeswyft.fielder.domain

import groovy.transform.CompileStatic
import io.micronaut.core.annotation.NonNull

import javax.persistence.Column
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MappedSuperclass
import javax.persistence.Transient

@CompileStatic
@MappedSuperclass
abstract class TransactionLineItemPO<T extends TransactionLineItemDetailPO> extends BasePO {

    @Column(name = 'number', nullable = false)
    @NonNull
    Integer number

    // The OrganizationItemPO id for Quotes and Invoices, the ItemPO id for ProductOrders
    @Column(name = 'item_id', nullable = false)
    @NonNull
    String itemId

    @Column(name = 'quantity', nullable = false)
    @NonNull
    Integer quantity = 0

    @Column(name = 'unit_price', nullable = false, precision = 15, scale = 2)
    @NonNull
    BigDecimal unitPrice = new BigDecimal(BigInteger.ZERO)

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tax_rate_group_id")
    TaxRateGroupPO taxRateGroup

    // Indicates whether bundle item components should be visible (by default)
    // on customer-facing renderings of this transaction.
    @Column(name = 'show_details', nullable = false)
    Boolean showDetails

    abstract Set<T> getLineItemDetails()

    abstract void setLineItemDetails(Set<T> lineItemDetails)

    @Transient
    BigDecimal getTotal() {
        return (unitPrice * quantity).round(2)
    }

}
