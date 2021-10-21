package com.codeswyft.fielder.model

import groovy.transform.CompileStatic

@CompileStatic
enum InvoiceStatus {
    DRAFT,
    APPROVED, // eligible to be sent to customer
    VOIDED,   // only used after an invoice is APPROVED
    ARCHIVED  // equates to Xero's DELETED
}
