package com.codeswyft.fielder.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.CompileStatic
import io.micronaut.core.annotation.NonNull

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@CompileStatic
@Table(name = 'invoice_line_item_detail')
class InvoiceLineItemDetailPO extends TransactionLineItemDetailPO {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_line_item_id", nullable = false)
    @NonNull
    InvoiceLineItemPO invoiceLineItem

}
