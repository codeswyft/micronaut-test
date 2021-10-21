package com.codeswyft.fielder.domain

import com.codeswyft.fielder.model.InvoiceStatus
import com.codeswyft.fielder.model.PluginProvider
import groovy.transform.CompileStatic
import io.micronaut.core.annotation.NonNull

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.Index
import javax.persistence.OneToMany
import javax.persistence.Table
import java.time.LocalDateTime

@Entity
@CompileStatic
@Table(
    name = 'invoice',
    indexes = [@Index(name = "idx_invoice_number_org", columnList = "number,organization_id", unique = true)])
class InvoicePO extends TransactionPO<InvoiceLineItemPO> {

    @Column(name = 'job_id', nullable = false, updatable = false)
    @NonNull
    String jobId

    @Column(name = 'billing_contact_id', nullable = false)
    @NonNull
    String billingContactId

    @Column(name = 'description', nullable = true, columnDefinition = 'TEXT')
    String description

    @Column(name = 'customer_note', nullable = true, columnDefinition = 'TEXT')
    String customerNote // Appears as a note on the customer view of the invoice.

    @Column(name = 'status', nullable = false)
    @NonNull
    @Enumerated(EnumType.STRING)
    InvoiceStatus status

    @Column(name = 'due_date', nullable = true)
    LocalDateTime dueDate

    @Column(name = 'plugin_provider', nullable = true)
    @Enumerated(EnumType.STRING)
    PluginProvider pluginProvider // Name of the plug-in service provider to which this Invoice was sent (i.e., Xero, Quickbooks, etc)

    @Column(name = 'plugin_provider_invoice_id', nullable = true)
    String pluginProviderInvoiceId // Identifier assigned by the service provider

    @Column(name = 'date_sent_to_customer', nullable = true)
    LocalDateTime dateSentToCustomer // Date on which the invoice was sent to customer

    @Column(name = 'date_sent_to_plugin_provider', nullable = true)
    LocalDateTime dateSentToPluginProvider // Date on which the invoice was sent to plug-in service provider

    //==========================================================================================
    // We want to continually update our cost as line items change
    //==========================================================================================

    @OneToMany(
        mappedBy = "invoice",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )
    private Set<InvoiceLineItemPO> lineItems = new HashSet<InvoiceLineItemPO>()

    Set<InvoiceLineItemPO> getLineItems() {
        return this.@lineItems.asUnmodifiable() // unmodifiable list!
    }

    void setLineItems(Set<InvoiceLineItemPO> lineItems) {
        this.@lineItems.clear()
        this.@lineItems.addAll(lineItems)
        recalculateCost()
    }

    void addLineItem(InvoiceLineItemPO lineItem) {
        this.@lineItems.add(lineItem)
        recalculateCost()
    }

    void addLineItems(Set<InvoiceLineItemPO> lineItems) {
        this.@lineItems.addAll(lineItems)
        recalculateCost()
    }

    void removeLineItem(InvoiceLineItemPO lineItem) {
        this.@lineItems.remove(lineItem)
        recalculateCost()
    }

    void removeLineItems(Set<InvoiceLineItemPO> lineItems) {
        this.@lineItems.removeAll(lineItems)
        recalculateCost()
    }

    void clearLineItems() {
        this.@lineItems.clear()
        recalculateCost()
    }
}
