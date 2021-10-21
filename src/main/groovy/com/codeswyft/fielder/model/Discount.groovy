package com.codeswyft.fielder.model

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

// Note that this is not a BaseNode.
// This class exists to encapsulate discount info
@CompileStatic
@EqualsAndHashCode
class Discount {

    BigDecimal value = 0.0
    DiscountType type = DiscountType.AMOUNT

}
