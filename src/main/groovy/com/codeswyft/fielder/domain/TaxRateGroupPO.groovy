package com.codeswyft.fielder.domain

import com.codeswyft.fielder.model.PluginProvider
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Transient

/**
 * TaxRateGroup is basically just a named grouping of individual taxRates.
 * It is meant to be compatible with Xero and QuickBook's data structures for the same purpose.
 */
@Entity
@CompileStatic
@Table(name = 'tax_rate_group')
@EqualsAndHashCode(callSuper = true,
    includes = [
        'organizationId',
        'name',
    ])
class TaxRateGroupPO extends BasePO {

    @Column(name = 'organization_id', nullable = false)
    String organizationId

    @Column(name = 'name', nullable = false)
    String name

    @Column(name = 'is_archived', nullable = false)
    Boolean isArchived

    @Column(name = 'is_locked', nullable = false)
    Boolean isLocked

    @Column(name = 'plugin_provider', nullable = true)
    @Enumerated(EnumType.STRING)
    PluginProvider pluginProvider // Name of the plug-in service provider that "owns" this Tax data

    @Column(name = 'plugin_provider_object_id', nullable = true)
    String pluginProviderObjectId // Identifier assigned by the service provider. (I.e., TaxType in Xero, or TaxCode.id in QBO).

    @OneToMany(
        mappedBy = "taxRateGroup",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )
    Set<TaxRatePO> taxRates = new HashSet<TaxRatePO>()

    @Transient
    BigDecimal getTotalTaxRate() {
        return taxRates.collect { it.rate }.sum(0) as BigDecimal
    }

}
