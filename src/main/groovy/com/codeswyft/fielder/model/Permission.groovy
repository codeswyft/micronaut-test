package com.codeswyft.fielder.model

import groovy.transform.CompileStatic

@CompileStatic
class Permission extends BaseNode {

    String name

    PermissionGroup group

    // user must belong to an org that is <= this level
    Integer requiredOrganizationLevel

}
