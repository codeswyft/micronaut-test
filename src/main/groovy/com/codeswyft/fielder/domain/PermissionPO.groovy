package com.codeswyft.fielder.domain

import com.codeswyft.fielder.model.PermissionGroup
import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@CompileStatic
@Table(name = 'permission')
class PermissionPO extends BasePO {

    /**
     * The name should be a mutation or query name from the GraphQL schema
     */
    @Column(name = 'name', nullable = false)
    String name

    @Column(name = 'permission_group', nullable = false)
    @Enumerated(EnumType.STRING)
    PermissionGroup group

    // user must belong to an org that is <= this level
    @Column(name = 'required_org_level', nullable = false)
    Integer requiredOrganizationLevel

    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    Set<RolePO> roles = new HashSet<RolePO>()

}
