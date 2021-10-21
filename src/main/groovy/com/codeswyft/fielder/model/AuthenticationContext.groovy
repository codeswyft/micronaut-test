package com.codeswyft.fielder.model

import com.codeswyft.fielder.graphql.validation.ValidationError
import groovy.transform.CompileStatic

@CompileStatic
class AuthenticationContext {

    AuthenticationContext() {}

    AuthenticationContext(ValidationError error) {
        this.validationError = error
    }

    String userId // a.k.a user's email
    String organizationId
    Integer organizationLevel
    String parentOrganizationId
    boolean isOrganizationOwner = false
    List<Role> roles = []
    List<String> allowedOperations = []
    String clientIp
    ValidationError validationError

}
