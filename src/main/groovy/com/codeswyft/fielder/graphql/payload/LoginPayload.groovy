package com.codeswyft.fielder.graphql.payload

import com.codeswyft.fielder.model.User
import groovy.transform.CompileStatic

@CompileStatic
class LoginPayload {

    String authToken
    User user

}
