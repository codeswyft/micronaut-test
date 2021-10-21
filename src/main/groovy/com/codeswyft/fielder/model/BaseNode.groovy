package com.codeswyft.fielder.model

import groovy.transform.CompileStatic

import java.time.LocalDateTime

@CompileStatic
class BaseNode {

    String id
    LocalDateTime createdAt
    LocalDateTime updatedAt
    String createdById
    String updatedById

}
