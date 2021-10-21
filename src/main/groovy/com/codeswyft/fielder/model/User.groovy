package com.codeswyft.fielder.model

import groovy.transform.CompileStatic

import java.time.LocalDateTime

@CompileStatic
class User extends BaseNode {

    String firstName
    String lastName
    String email
    String jobTitle
    String mobilePhoneNumber
    UserStatus status = UserStatus.UNVERIFIED
    LocalDateTime lastLogin
    List<String> roleIds = []
    String organizationId
    String timeZone

}
