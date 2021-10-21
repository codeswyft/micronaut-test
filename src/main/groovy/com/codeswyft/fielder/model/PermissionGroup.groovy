package com.codeswyft.fielder.model

import groovy.transform.CompileStatic

@CompileStatic
enum PermissionGroup {
    ANY,
    CHECKLIST,
    CREW,
    CUSTOMER,
    INVOICE,
    ITEM,
    JOB,
    JOB_ASSIGNMENT,
    JOB_CATEGORY,
    JOB_STATUS,
    JOB_WORKFLOW,
    ORGANIZATION,
    ORGANIZATION_ITEM,
    ORGANIZATION_PLUGIN,
    PAYMENT,
    PRODUCT_ORDER,
    QUOTE,
    QUOTE_REQUEST,
    REGION,
    ROLE_MANAGEMENT,
    TASK,
    TAX_RATE_GROUP,
    USER,
    WORK_ORDER
}
