package com.codeswyft.fielder.model

import groovy.transform.CompileStatic

@CompileStatic
class TaxRateAmount extends BaseNode {

    TaxRate taxRate
    BigDecimal amount

}
