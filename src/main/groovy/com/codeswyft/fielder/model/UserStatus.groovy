package com.codeswyft.fielder.model

import groovy.transform.CompileStatic

@CompileStatic
enum UserStatus {
    UNVERIFIED,
    ACTIVE,
    LOCKED,
    LOCKED_NEEDS_PASSWORD_CHANGE,
    DEACTIVATED
}
