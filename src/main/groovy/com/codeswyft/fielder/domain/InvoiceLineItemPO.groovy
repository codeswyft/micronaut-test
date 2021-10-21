package com.codeswyft.fielder.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.CompileStatic
import io.micronaut.core.annotation.NonNull

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@CompileStatic
@Table(name = 'invoice_line_item')
class InvoiceLineItemPO extends TransactionLineItemPO<InvoiceLineItemDetailPO> {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false)
    @NonNull
    InvoicePO invoice

    // This field is lazy-loaded with a @JsonIgnore annotation because it should be loaded via GraphQL resolver if it's needed.
    @JsonIgnore
    @OneToMany(
        mappedBy = "invoiceLineItem",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private Set<InvoiceLineItemDetailPO> lineItemDetails = new HashSet<InvoiceLineItemDetailPO>()

    @Override
    Set<InvoiceLineItemDetailPO> getLineItemDetails() {
        return this.@lineItemDetails.asUnmodifiable() // unmodifiable list!
    }

    @Override
    void setLineItemDetails(Set<InvoiceLineItemDetailPO> lineItemDetails) {
        this.@lineItemDetails.clear()
        this.@lineItemDetails.addAll(lineItemDetails)
    }
}
