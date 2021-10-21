package com.codeswyft.fielder.model

import groovy.transform.CompileStatic

@CompileStatic
enum DiscountType {
    PERCENTAGE, // percentage used in calculating amount to subtract
    AMOUNT // explicit numerical amount to subtract
}
