package com.codeswyft.fielder.domain

import groovy.transform.CompileStatic
import io.micronaut.core.annotation.NonNull

import javax.persistence.Column
import javax.persistence.MappedSuperclass

@CompileStatic
@MappedSuperclass
abstract class TransactionLineItemDetailPO extends BasePO {

    @Column(name = "organization_item_id", nullable = false)
    @NonNull
    String itemId //must be a Non Group Item.

    @Column(name = 'unit_sale_price', nullable = false)
    BigDecimal unitSalePrice

    @Column(name = 'quantity', nullable = false)
    Integer quantity

}
