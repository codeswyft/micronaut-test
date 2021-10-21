package com.codeswyft.fielder.model

import groovy.transform.CompileStatic

@CompileStatic
class TaxSummary extends BaseNode {

    BigDecimal total = 0
    List<TaxRateAmount> taxRateAmounts = []

}
