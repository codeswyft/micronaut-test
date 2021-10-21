package com.codeswyft.fielder.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import io.micronaut.core.annotation.NonNull

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 * TaxRateGroup is basically just a named grouping of individual taxRates.
 */
@Entity
@CompileStatic
@Table(name = 'tax_rate')
@EqualsAndHashCode(callSuper = true,
    includes = [
        'name',
        'rate'
    ])
class TaxRatePO extends BasePO {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_rate_group_id", nullable = false)
    @NonNull
    TaxRateGroupPO taxRateGroup

    @Column(name = 'name', nullable = false)
    String name

    /**
     * The tax rate (percentage).
     * Can be a real number in the range [0, 1], with up to 6 decimal places.
     * If you think of a tax rate as, say, 13.1625%, that would be stored as 0.131625
     * Copying Xero's specs for rates here (their tax rates support up to 4 decimal places).
     */
    @Column(name = 'rate', nullable = false, precision = 7, scale = 6)
    BigDecimal rate

}
