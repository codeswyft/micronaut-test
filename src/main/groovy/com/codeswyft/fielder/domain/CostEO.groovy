package com.codeswyft.fielder.domain

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.Immutable
import io.micronaut.core.annotation.NonNull

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
@CompileStatic
@Immutable
@EqualsAndHashCode
class CostEO {

    @NonNull
    @Column(name = 'sub_total', nullable = false, precision = 15, scale = 2)
    BigDecimal subTotal = BigDecimal.ZERO

    @NonNull
    @Column(name = 'total_tax', nullable = false, precision = 15, scale = 2)
    BigDecimal totalTax = BigDecimal.ZERO

    @NonNull
    @Column(name = 'total_discount', nullable = false, precision = 15, scale = 2)
    BigDecimal totalDiscount = BigDecimal.ZERO

    @NonNull
    @Column(name = 'total', nullable = false, precision = 15, scale = 2)
    BigDecimal total = BigDecimal.ZERO

}
