package com.codeswyft.fielder.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Index
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@CompileStatic
@Table(
    name = 'role',
    indexes = [@Index(name = "idx_role_name_org", columnList = "name,organization_id", unique = true)]
)
class RolePO extends BasePO {

    @Column(name = 'name', nullable = false)
    String name

    @Column(name = 'description', nullable = true, columnDefinition = 'TEXT')
    String description

    @Column(name = 'organization_id', nullable = false, updatable = false)
    String organizationId

    /**
     * This flag indicates whether this Role is a "standard" (system-defined)
     * Role. If it is, we generally shouldn't allow edits, and every Organization
     * should be able to use it.
     */
    @Column(name = 'is_standard', nullable = false, updatable = false)
    Boolean isStandard = false

    // user must belong to an organization with a level that is <= to this level
    // NOTE: This field is calculated based on the MIN(permissions*.requiredOrganizationLevel) when standard roles is created,
    // and is used in JPA queries.
    @Column(name = 'required_org_level', nullable = false)
    Integer requiredOrganizationLevel

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "role_permission",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    Set<PermissionPO> permissions = new HashSet<PermissionPO>()

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    Set<UserPO> users = new HashSet<UserPO>()

}
