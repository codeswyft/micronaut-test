package com.codeswyft.fielder.graphql.input

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic

@CompileStatic
class LoginInput {

    String username
    String password

    @JsonIgnore
    String getPassword() {
        return password
    }

    @JsonProperty
    void setPassword(String password) {
        this.password = password
    }

}
