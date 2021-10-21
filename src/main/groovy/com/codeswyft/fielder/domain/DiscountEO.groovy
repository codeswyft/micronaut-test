package com.codeswyft.fielder.domain

import com.codeswyft.fielder.model.DiscountType
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.Immutable
import io.micronaut.core.annotation.NonNull

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
@CompileStatic
@Immutable
@EqualsAndHashCode
class DiscountEO {

    // if type is PERCENT, store as a decimal >= 0.0 and <= 1.00
    @Column(nullable = false, name = 'discount_value')
    @NonNull
    BigDecimal value = BigDecimal.ZERO

    @Column(nullable = false, name = 'discount_type')
    @Enumerated(EnumType.STRING)
    @NonNull
    DiscountType type = DiscountType.AMOUNT

}
