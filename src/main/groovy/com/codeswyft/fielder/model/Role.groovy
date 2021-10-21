package com.codeswyft.fielder.model

import groovy.transform.CompileStatic

@CompileStatic
class Role extends BaseNode {

    String name

    String description

    // this property is read-only
    Boolean isStandard

    // user must belong to an org that is <= this level
    Integer requiredOrganizationLevel

    // this is only used for creating/updating a Role.
    // Don't expect it to be populated when querying roles (it won't be).
    // We should load associations lazily as much as possible. If the client wants the
    // role's permissions, they'll be loaded via RoleResolver.
    List<String> permissionIds = []

    // The 'organization' should be resolved by RoleResolver, if requested, during a query.
    // Or, when creating/editing, it should be set by RoleModule by taking it off the AuthenticationContext for the logged-in user
    String organizationId

}
