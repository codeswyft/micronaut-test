package com.codeswyft.fielder.util.dataseed

import com.codeswyft.fielder.domain.PermissionPO
import com.codeswyft.fielder.model.PermissionGroup
import groovy.transform.CompileStatic

@CompileStatic
class DefaultPermissions {

    /**
     * REGIONS
     */
    static final PermissionPO ALL_REGIONS = new PermissionPO(id: 'allRegions', name: "allRegions", group: PermissionGroup.REGION, requiredOrganizationLevel: 1)
    static final PermissionPO CREATE_REGION = new PermissionPO(id: 'createRegion', name: "createRegion", group: PermissionGroup.REGION, requiredOrganizationLevel: 1)
    static final PermissionPO EDIT_REGION = new PermissionPO(id: 'editRegion', name: "editRegion", group: PermissionGroup.REGION, requiredOrganizationLevel: 1)
    static final List<PermissionPO> ALL_REGION_PERMISSIONS = [
        ALL_REGIONS,
        CREATE_REGION,
        EDIT_REGION,
    ]

    /**
     * TAX RATE GROUPS
     */
    static final PermissionPO ALL_TAX_RATE_GROUPS = new PermissionPO(id: 'allTaxRateGroups', name: "allTaxRateGroups", group: PermissionGroup.TAX_RATE_GROUP, requiredOrganizationLevel: 2)
    static final PermissionPO CREATE_TAX_RATE_GROUP = new PermissionPO(id: 'createTaxRateGroup', name: "createTaxRateGroup", group: PermissionGroup.TAX_RATE_GROUP, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_TAX_RATE_GROUP = new PermissionPO(id: 'editTaxRateGroup', name: "editTaxRateGroup", group: PermissionGroup.TAX_RATE_GROUP, requiredOrganizationLevel: 2)
    static final PermissionPO SYNC_TAX_RATE_GROUPS = new PermissionPO(id: 'syncTaxRateGroups', name: "syncTaxRateGroups", group: PermissionGroup.TAX_RATE_GROUP, requiredOrganizationLevel: 2)
    static final List<PermissionPO> ALL_TAX_RATE_GROUP_PERMISSIONS = [
        ALL_TAX_RATE_GROUPS,
        CREATE_TAX_RATE_GROUP,
        EDIT_TAX_RATE_GROUP,
        SYNC_TAX_RATE_GROUPS
    ]

    /**
     * JOB STATUSES
     */
    static final PermissionPO CREATE_JOB_STATUS = new PermissionPO(id: 'createJobStatus', name: "createJobStatus", group: PermissionGroup.JOB_STATUS, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_JOB_STATUS = new PermissionPO(id: 'editJobStatus', name: "editJobStatus", group: PermissionGroup.JOB_STATUS, requiredOrganizationLevel: 2)
    static final PermissionPO ALL_JOB_STATUSES = new PermissionPO(id: 'allJobStatuses', name: 'allJobStatuses', group: PermissionGroup.JOB_STATUS, requiredOrganizationLevel: 2)
    static final PermissionPO GET_JOB_STATUS_BY_ID = new PermissionPO(id: 'getJobStatusById', name: 'getJobStatusById', group: PermissionGroup.JOB_STATUS, requiredOrganizationLevel: 2)
    static final List<PermissionPO> ALL_JOB_STATUS_PERMISSIONS = [
        CREATE_JOB_STATUS,
        EDIT_JOB_STATUS,
        ALL_JOB_STATUSES,
        GET_JOB_STATUS_BY_ID
    ]

    /**
     * JOB CATEGORIES
     */
    static final PermissionPO CREATE_JOB_CATEGORY = new PermissionPO(id: 'createJobCategory', name: "createJobCategory", group: PermissionGroup.JOB_CATEGORY, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_JOB_CATEGORY = new PermissionPO(id: 'editJobCategory', name: "editJobCategory", group: PermissionGroup.JOB_CATEGORY, requiredOrganizationLevel: 2)
    static final PermissionPO ALL_JOB_CATEGORIES = new PermissionPO(id: 'allJobCategories', name: 'allJobCategories', group: PermissionGroup.JOB_CATEGORY, requiredOrganizationLevel: 2)
    static final PermissionPO GET_JOB_CATEGORY_BY_ID = new PermissionPO(id: 'getJobCategoryById', name: 'getJobCategoryById', group: PermissionGroup.JOB_CATEGORY, requiredOrganizationLevel: 2)
    static final PermissionPO ARCHIVE_JOB_CATEGORY = new PermissionPO(id: 'archiveJobCategory', name: 'archiveJobCategory', group: PermissionGroup.JOB_CATEGORY, requiredOrganizationLevel: 2)
    static final List<PermissionPO> ALL_JOB_CATEGORY_PERMISSIONS = [
        CREATE_JOB_CATEGORY,
        EDIT_JOB_CATEGORY,
        ALL_JOB_CATEGORIES,
        GET_JOB_CATEGORY_BY_ID,
        ARCHIVE_JOB_CATEGORY
    ]

    /**
     * JOBS
     */
    static final PermissionPO CREATE_JOB = new PermissionPO(id: 'createJob', name: 'createJob', group: PermissionGroup.JOB, requiredOrganizationLevel: 2)
    static final PermissionPO CLONE_JOB = new PermissionPO(id: 'cloneJob', name: 'cloneJob', group: PermissionGroup.JOB, requiredOrganizationLevel: 2)
    static final PermissionPO CREATE_JOB_NOTE = new PermissionPO(id: 'createJobNote', name: 'createJobNote', group: PermissionGroup.JOB, requiredOrganizationLevel: 2)
    static final PermissionPO SEND_JOB_EMAIL = new PermissionPO(id: 'sendJobEmail', name: 'sendJobEmail', group: PermissionGroup.JOB, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_JOB_NOTE = new PermissionPO(id: 'editJobNote', name: 'editJobNote', group: PermissionGroup.JOB, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_JOB = new PermissionPO(id: 'editJob', name: 'editJob', group: PermissionGroup.JOB, requiredOrganizationLevel: 2)
    static final PermissionPO ALL_JOBS = new PermissionPO(id: 'allJobs', name: 'allJobs', group: PermissionGroup.JOB, requiredOrganizationLevel: 2)
    static final PermissionPO GET_JOB_BY_ID = new PermissionPO(id: 'getJobById', name: 'getJobById', group: PermissionGroup.JOB, requiredOrganizationLevel: 2)
    static final PermissionPO SEND_SMS_TO_JOB_CONTACT = new PermissionPO(id: 'sendSmsToJobContact', name: 'sendSmsToJobContact', group: PermissionGroup.JOB, requiredOrganizationLevel: 2)
    static final PermissionPO ARCHIVE_JOB = new PermissionPO(id: 'archiveJob', name: 'archiveJob', group: PermissionGroup.JOB, requiredOrganizationLevel: 2)
    static final List<PermissionPO> ALL_JOB_PERMISSIONS = [
        CREATE_JOB,
        CLONE_JOB,
        CREATE_JOB_NOTE,
        SEND_JOB_EMAIL,
        EDIT_JOB_NOTE,
        EDIT_JOB,
        ALL_JOBS,
        GET_JOB_BY_ID,
        SEND_SMS_TO_JOB_CONTACT,
        ARCHIVE_JOB
    ]

    /**
     * JOB ASSIGNMENTS
     */
    static final PermissionPO CREATE_JOB_ASSIGNMENT = new PermissionPO(id: 'createJobAssignment', name: 'createJobAssignment', group: PermissionGroup.JOB_ASSIGNMENT, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_JOB_ASSIGNMENT = new PermissionPO(id: 'editJobAssignment', name: 'editJobAssignment', group: PermissionGroup.JOB_ASSIGNMENT, requiredOrganizationLevel: 2)
    static final PermissionPO ALL_JOB_ASSIGNMENTS = new PermissionPO(id: 'allJobAssignments', name: 'allJobAssignments', group: PermissionGroup.JOB_ASSIGNMENT, requiredOrganizationLevel: 2)
    static final PermissionPO GET_JOB_ASSIGNMENT_BY_ID = new PermissionPO(id: 'getJobAssignmentById', name: 'getJobAssignmentById', group: PermissionGroup.JOB_ASSIGNMENT, requiredOrganizationLevel: 2)
    static final PermissionPO DELETE_JOB_ASSIGNMENT = new PermissionPO(id: 'deleteJobAssignment', name: 'deleteJobAssignment', group: PermissionGroup.JOB_ASSIGNMENT, requiredOrganizationLevel: 2)
    static final List<PermissionPO> ALL_JOB_ASSIGNMENT_PERMISSIONS = [
        CREATE_JOB_ASSIGNMENT,
        EDIT_JOB_ASSIGNMENT,
        ALL_JOB_ASSIGNMENTS,
        GET_JOB_ASSIGNMENT_BY_ID,
        DELETE_JOB_ASSIGNMENT
    ]

    /**
     * CREWS
     */
    static final PermissionPO CREATE_CREW = new PermissionPO(id: 'createCrew', name: 'createCrew', group: PermissionGroup.CREW, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_CREW = new PermissionPO(id: 'editCrew', name: 'editCrew', group: PermissionGroup.CREW, requiredOrganizationLevel: 2)
    static final PermissionPO GET_CREW_BY_ID = new PermissionPO(id: 'getCrewById', name: 'getCrewById', group: PermissionGroup.CREW, requiredOrganizationLevel: 2)
    static final PermissionPO DELETE_CREW = new PermissionPO(id: 'deleteCrew', name: 'deleteCrew', group: PermissionGroup.CREW, requiredOrganizationLevel: 2)
    static final PermissionPO ALL_CREWS = new PermissionPO(id: 'allCrews', name: 'allCrews', group: PermissionGroup.CREW, requiredOrganizationLevel: 2)
    static final List<PermissionPO> ALL_CREW_PERMISSIONS = [
        CREATE_CREW,
        EDIT_CREW,
        GET_CREW_BY_ID,
        DELETE_CREW,
        ALL_CREWS
    ]

    /**
     * JOB WORKFLOWS
     */
    static final PermissionPO ALL_JOB_WORKFLOWS = new PermissionPO(id: 'allJobWorkflows', name: "allJobWorkflows", group: PermissionGroup.JOB_WORKFLOW, requiredOrganizationLevel: 2)
    static final PermissionPO ALL_JOB_WORKFLOW_STEP_EVENTS = new PermissionPO(id: 'allJobWorkflowStepEvents', name: "allJobWorkflowStepEvents", group: PermissionGroup.JOB_WORKFLOW, requiredOrganizationLevel: 2)
    static final PermissionPO GET_JOB_WORKLOW_BY_ID = new PermissionPO(id: 'getJobWorkflowById', name: "getJobWorkflowById", group: PermissionGroup.JOB_WORKFLOW, requiredOrganizationLevel: 2)
    static final PermissionPO CREATE_JOB_WORKFLOW = new PermissionPO(id: 'createJobWorkflow', name: "createJobWorkflow", group: PermissionGroup.JOB_WORKFLOW, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_JOB_WORKFLOW = new PermissionPO(id: 'editJobWorkflow', name: "editJobWorkflow", group: PermissionGroup.JOB_WORKFLOW, requiredOrganizationLevel: 2)
    static final PermissionPO CREATE_JOB_WORKFLOW_STEP = new PermissionPO(id: 'createJobWorkflowStep', name: "createJobWorkflowStep", group: PermissionGroup.JOB_WORKFLOW, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_JOB_WORKFLOW_STEP = new PermissionPO(id: 'editJobWorkflowStep', name: "editJobWorkflowStep", group: PermissionGroup.JOB_WORKFLOW, requiredOrganizationLevel: 2)
    static final PermissionPO DELETE_JOB_WORKFLOW_STEP = new PermissionPO(id: 'deleteJobWorkflowStep', name: "deleteJobWorkflowStep", group: PermissionGroup.JOB_WORKFLOW, requiredOrganizationLevel: 2)
    static final PermissionPO CREATE_JOB_WORKFLOW_STEP_TRANSITION = new PermissionPO(id: 'createJobWorkflowStepTransition', name: "createJobWorkflowStepTransition", group: PermissionGroup.JOB_WORKFLOW, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_JOB_WORKFLOW_STEP_TRANSITION = new PermissionPO(id: 'editJobWorkflowStepTransition', name: "editJobWorkflowStepTransition", group: PermissionGroup.JOB_WORKFLOW, requiredOrganizationLevel: 2)

    static final List<PermissionPO> ALL_JOB_WORKFLOW_PERMISSIONS = [
        ALL_JOB_WORKFLOWS,
        ALL_JOB_WORKFLOW_STEP_EVENTS,
        GET_JOB_WORKLOW_BY_ID,
        CREATE_JOB_WORKFLOW,
        EDIT_JOB_WORKFLOW,
        CREATE_JOB_WORKFLOW_STEP,
        EDIT_JOB_WORKFLOW_STEP,
        DELETE_JOB_WORKFLOW_STEP,
        CREATE_JOB_WORKFLOW_STEP_TRANSITION,
        EDIT_JOB_WORKFLOW_STEP_TRANSITION
    ]

    /**
     * TASKS
     */
    static final PermissionPO CREATE_TASK = new PermissionPO(id: 'createTask', name: 'createTask', group: PermissionGroup.TASK, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_TASK = new PermissionPO(id: 'editTask', name: 'editTask', group: PermissionGroup.TASK, requiredOrganizationLevel: 2)
    static final PermissionPO ALL_TASKS = new PermissionPO(id: 'allTasks', name: 'allTasks', group: PermissionGroup.TASK, requiredOrganizationLevel: 2)
    static final PermissionPO GET_TASK_BY_ID = new PermissionPO(id: 'getTaskById', name: 'getTaskById', group: PermissionGroup.TASK, requiredOrganizationLevel: 2)
    static final List<PermissionPO> ALL_TASK_PERMISSIONS = [
        CREATE_TASK,
        EDIT_TASK,
        ALL_TASKS,
        GET_TASK_BY_ID,
    ]

    /**
     * QUOTES
     */
    static final PermissionPO CREATE_QUOTE = new PermissionPO(id: 'createQuote', name: 'createQuote', group: PermissionGroup.QUOTE, requiredOrganizationLevel: 2)
    static final PermissionPO CLONE_QUOTE = new PermissionPO(id: 'cloneQuote', name: 'cloneQuote', group: PermissionGroup.QUOTE, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_QUOTE = new PermissionPO(id: 'editQuote', name: 'editQuote', group: PermissionGroup.QUOTE, requiredOrganizationLevel: 2)
    static final PermissionPO SEND_QUOTE_EMAIL = new PermissionPO(id: 'sendQuoteEmail', name: 'sendQuoteEmail', group: PermissionGroup.QUOTE, requiredOrganizationLevel: 2)
    static final PermissionPO ALL_QUOTES_FOR_JOB = new PermissionPO(id: 'allQuotesForJob', name: 'allQuotesForJob', group: PermissionGroup.QUOTE, requiredOrganizationLevel: 2)
    static final PermissionPO GET_QUOTE_BY_ID = new PermissionPO(id: 'getQuoteById', name: 'getQuoteById', group: PermissionGroup.QUOTE, requiredOrganizationLevel: 2)
    static final PermissionPO ALL_QUOTE_TEMPLATES = new PermissionPO(id: 'allQuoteTemplates', name: 'allQuoteTemplates', group: PermissionGroup.QUOTE, requiredOrganizationLevel: 2)
    static final PermissionPO GET_QUOTE_TEMPLATE_BY_ID = new PermissionPO(id: 'getQuoteTemplateById', name: 'getQuoteTemplateById', group: PermissionGroup.QUOTE, requiredOrganizationLevel: 2)
    static final PermissionPO CREATE_QUOTE_TEMPLATE = new PermissionPO(id: 'createQuoteTemplate', name: 'createQuoteTemplate', group: PermissionGroup.QUOTE, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_QUOTE_TEMPLATE = new PermissionPO(id: 'editQuoteTemplate', name: 'editQuoteTemplate', group: PermissionGroup.QUOTE, requiredOrganizationLevel: 2)
    static final PermissionPO DELETE_QUOTE_TEMPLATE = new PermissionPO(id: 'deleteQuoteTemplate', name: 'deleteQuoteTemplate', group: PermissionGroup.QUOTE, requiredOrganizationLevel: 2)

    static final List<PermissionPO> ALL_QUOTE_PERMISSIONS = [
        CREATE_QUOTE,
        CLONE_QUOTE,
        EDIT_QUOTE,
        SEND_QUOTE_EMAIL,
        ALL_QUOTES_FOR_JOB,
        GET_QUOTE_BY_ID,
        CREATE_QUOTE_TEMPLATE,
        ALL_QUOTE_TEMPLATES,
        GET_QUOTE_TEMPLATE_BY_ID,
        EDIT_QUOTE_TEMPLATE,
        DELETE_QUOTE_TEMPLATE
    ]

    /**
     * WORK ORDERS
     */
    static final PermissionPO CREATE_WORK_ORDER = new PermissionPO(id: 'createWorkOrder', name: 'createWorkOrder', group: PermissionGroup.WORK_ORDER, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_WORK_ORDER = new PermissionPO(id: 'editWorkOrder', name: 'editWorkOrder', group: PermissionGroup.WORK_ORDER, requiredOrganizationLevel: 2)
    static final PermissionPO GET_WORK_ORDER_BY_ID = new PermissionPO(id: 'getWorkOrderById', name: 'getWorkOrderById', group: PermissionGroup.WORK_ORDER, requiredOrganizationLevel: 2)
    static final PermissionPO CREATE_WORK_ORDER_TEMPLATE = new PermissionPO(id: 'createWorkOrderTemplate', name: 'createWorkOrderTemplate', group: PermissionGroup.WORK_ORDER, requiredOrganizationLevel: 2)
    static final PermissionPO GET_WORK_ORDER_TEMPLATE_BY_ID = new PermissionPO(id: 'getWorkOrderTemplateById', name: 'getWorkOrderTemplateById', group: PermissionGroup.WORK_ORDER, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_WORK_ORDER_TEMPLATE = new PermissionPO(id: 'editWorkOrderTemplate', name: 'editWorkOrderTemplate', group: PermissionGroup.WORK_ORDER, requiredOrganizationLevel: 2)
    static final PermissionPO DELETE_WORK_ORDER_TEMPLATE = new PermissionPO(id: 'deleteWorkOrderTemplate', name: 'deleteWorkOrderTemplate', group: PermissionGroup.WORK_ORDER, requiredOrganizationLevel: 2)
    static final PermissionPO ALL_WORK_ORDER_TEMPLATES = new PermissionPO(id: 'allWorkOrderTemplates', name: 'allWorkOrderTemplates', group: PermissionGroup.WORK_ORDER, requiredOrganizationLevel: 2)
    static final List<PermissionPO> ALL_WORK_ORDER_PERMISSIONS = [
        CREATE_WORK_ORDER,
        EDIT_WORK_ORDER,
        GET_WORK_ORDER_BY_ID,
        CREATE_WORK_ORDER_TEMPLATE,
        GET_WORK_ORDER_TEMPLATE_BY_ID,
        EDIT_WORK_ORDER_TEMPLATE,
        DELETE_WORK_ORDER_TEMPLATE,
        ALL_WORK_ORDER_TEMPLATES
    ]

    /**
     * INVOICES
     */
    static final PermissionPO CREATE_INVOICE = new PermissionPO(id: 'createInvoice', name: 'createInvoice', group: PermissionGroup.INVOICE, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_INVOICE = new PermissionPO(id: 'editInvoice', name: 'editInvoice', group: PermissionGroup.INVOICE, requiredOrganizationLevel: 2)
    static final PermissionPO ALL_INVOICES = new PermissionPO(id: 'allInvoices', name: 'allInvoices', group: PermissionGroup.INVOICE, requiredOrganizationLevel: 2)
    static final PermissionPO GET_INVOICE_BY_ID = new PermissionPO(id: 'getInvoiceById', name: 'getInvoiceById', group: PermissionGroup.INVOICE, requiredOrganizationLevel: 2)
    static final PermissionPO SEND_INVOICE_TO_CUSTOMER = new PermissionPO(id: 'sendInvoiceToCustomer', name: 'sendInvoiceToCustomer', group: PermissionGroup.INVOICE, requiredOrganizationLevel: 2)
    static final PermissionPO SEND_INVOICE_TO_PROVIDER = new PermissionPO(id: 'sendInvoiceToProvider', name: 'sendInvoiceToProvider', group: PermissionGroup.INVOICE, requiredOrganizationLevel: 2)
    static final List<PermissionPO> ALL_INVOICE_PERMISSIONS = [
        CREATE_INVOICE,
        EDIT_INVOICE,
        ALL_INVOICES,
        GET_INVOICE_BY_ID,
        SEND_INVOICE_TO_CUSTOMER,
        SEND_INVOICE_TO_PROVIDER
    ]

    /**
     * CHECKLIST
     */
    static final PermissionPO ALL_CHECKLIST_TEMPLATES = new PermissionPO(id: 'allChecklistTemplates', name: 'allChecklistTemplates', group: PermissionGroup.CHECKLIST, requiredOrganizationLevel: 2)
    static final PermissionPO GET_CHECKLIST_TEMPLATE_BY_ID = new PermissionPO(id: 'getChecklistTemplateById', name: 'getChecklistTemplateById', group: PermissionGroup.CHECKLIST, requiredOrganizationLevel: 2)
    static final PermissionPO CREATE_CHECKLIST_TEMPLATE = new PermissionPO(id: 'createChecklistTemplate', name: 'createChecklistTemplate', group: PermissionGroup.CHECKLIST, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_CHECKLIST_TEMPLATE = new PermissionPO(id: 'editChecklistTemplate', name: 'editChecklistTemplate', group: PermissionGroup.CHECKLIST, requiredOrganizationLevel: 2)
    static final PermissionPO DELETE_CHECKLIST_TEMPLATE = new PermissionPO(id: 'deleteChecklistTemplate', name: 'deleteChecklistTemplate', group: PermissionGroup.CHECKLIST, requiredOrganizationLevel: 2)
    static final PermissionPO CREATE_CHECKLIST = new PermissionPO(id: 'createChecklist', name: 'createChecklist', group: PermissionGroup.CHECKLIST, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_CHECKLIST = new PermissionPO(id: 'editChecklist', name: 'editChecklist', group: PermissionGroup.CHECKLIST, requiredOrganizationLevel: 2)
    static final PermissionPO GET_CHECKLIST = new PermissionPO(id: 'getChecklistForJob', name: 'getChecklistForJob', group: PermissionGroup.CHECKLIST, requiredOrganizationLevel: 2)
    static final PermissionPO CREATE_CHECKLIST_LINE_ITEM_NOTE = new PermissionPO(id: 'createChecklistLineItemNote', name: 'createChecklistLineItemNote', group: PermissionGroup.CHECKLIST, requiredOrganizationLevel: 2)
    static final List<PermissionPO> ALL_CHECKLIST_PERMISSIONS = [
        ALL_CHECKLIST_TEMPLATES,
        GET_CHECKLIST_TEMPLATE_BY_ID,
        CREATE_CHECKLIST_TEMPLATE,
        EDIT_CHECKLIST_TEMPLATE,
        DELETE_CHECKLIST_TEMPLATE,
        CREATE_CHECKLIST,
        EDIT_CHECKLIST,
        GET_CHECKLIST,
        CREATE_CHECKLIST_LINE_ITEM_NOTE
    ]

    /**
     * PAYMENTS
     */
    static final PermissionPO CREATE_PAYMENT = new PermissionPO(id: 'createPayment', name: 'createPayment', group: PermissionGroup.PAYMENT, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_PAYMENT = new PermissionPO(id: 'editPayment', name: 'editPayment', group: PermissionGroup.PAYMENT, requiredOrganizationLevel: 2)
    static final List<PermissionPO> ALL_PAYMENT_PERMISSIONS = [
        CREATE_PAYMENT,
        EDIT_PAYMENT,
    ]

    /**
     * QUOTE REQUESTS
     */
    static final PermissionPO ALL_QUOTE_REQUESTS = new PermissionPO(id: 'allQuoteRequests', name: 'allQuoteRequests', group: PermissionGroup.QUOTE_REQUEST, requiredOrganizationLevel: 2)
    static final PermissionPO GET_QUOTE_REQUEST_BY_ID = new PermissionPO(id: 'getQuoteRequestById', name: 'getQuoteRequestById', group: PermissionGroup.QUOTE_REQUEST, requiredOrganizationLevel: 2)
    static final PermissionPO ARCHIVE_QUOTE_REQUEST = new PermissionPO(id: 'archiveQuoteRequest', name: 'archiveQuoteRequest', group: PermissionGroup.QUOTE_REQUEST, requiredOrganizationLevel: 2)
    static final PermissionPO CREATE_JOB_FROM_QUOTE_REQUEST = new PermissionPO(id: 'createJobFromQuoteRequest', name: 'createJobFromQuoteRequest', group: PermissionGroup.QUOTE_REQUEST, requiredOrganizationLevel: 2)
    static final List<PermissionPO> ALL_QUOTE_REQUEST_PERMISSIONS = [
        ALL_QUOTE_REQUESTS,
        GET_QUOTE_REQUEST_BY_ID,
        ARCHIVE_QUOTE_REQUEST,
        CREATE_JOB_FROM_QUOTE_REQUEST,
    ]

    /**
     * USERS
     */
    static final PermissionPO CREATE_USER = new PermissionPO(id: 'createUser', name: 'createUser', group: PermissionGroup.USER, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_USER = new PermissionPO(id: 'editUser', name: 'editUser', group: PermissionGroup.USER, requiredOrganizationLevel: 2)
    static final PermissionPO SEND_ACCOUNT_ACTIVATION_CODE = new PermissionPO(id: 'sendAccountActivationCode', name: 'sendAccountActivationCode', group: PermissionGroup.USER, requiredOrganizationLevel: 2)
    static final PermissionPO GET_USER = new PermissionPO(id: 'getUserById', name: 'getUserById', group: PermissionGroup.USER, requiredOrganizationLevel: 2)
    static final PermissionPO ALL_USERS = new PermissionPO(id: 'allUsers', name: 'allUsers', group: PermissionGroup.USER, requiredOrganizationLevel: 2)
    static final PermissionPO CREATE_USER_LOCATION = new PermissionPO(id: 'createUserLocation', name: "createUserLocation", group: PermissionGroup.USER, requiredOrganizationLevel: 2)
    static final PermissionPO GET_MY_PROFILE = new PermissionPO(id: 'getMyProfile', name: "getMyProfile", group: PermissionGroup.USER, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_MY_PROFILE = new PermissionPO(id: 'editMyProfile', name: "editMyProfile", group: PermissionGroup.USER, requiredOrganizationLevel: 2)
    static final List<PermissionPO> ALL_USER_PERMISSIONS = [
        CREATE_USER,
        EDIT_USER,
        SEND_ACCOUNT_ACTIVATION_CODE,
        GET_USER,
        ALL_USERS,
        CREATE_USER_LOCATION,
        GET_MY_PROFILE,
        EDIT_MY_PROFILE
    ]

    /**
     * ORGANIZATIONS
     */
    static final PermissionPO CREATE_ORGANIZATION = new PermissionPO(id: 'createOrganization', name: 'createOrganization', group: PermissionGroup.ORGANIZATION, requiredOrganizationLevel: 1)
    static final PermissionPO EDIT_ORGANIZATION = new PermissionPO(id: 'editOrganization', name: 'editOrganization', group: PermissionGroup.ORGANIZATION, requiredOrganizationLevel: 2)
    static final PermissionPO GET_ORGANIZATION_BY_ID = new PermissionPO(id: 'getOrganizationById', name: 'getOrganizationById', group: PermissionGroup.ORGANIZATION, requiredOrganizationLevel: 2)
    static final PermissionPO ALL_ORGANIZATIONS = new PermissionPO(id: 'allOrganizations', name: 'allOrganizations', group: PermissionGroup.ORGANIZATION, requiredOrganizationLevel: 1)
    static final PermissionPO DELETE_ORGANIZATION = new PermissionPO(id: 'deleteOrganization', name: 'deleteOrganization', group: PermissionGroup.ORGANIZATION, requiredOrganizationLevel: 0)
    static final PermissionPO CREATE_SUBSCRIPTION = new PermissionPO(id: 'createSubscription', name: 'createSubscription', group: PermissionGroup.ORGANIZATION, requiredOrganizationLevel: 2)
    static final PermissionPO SYNC_SUBSCRIPTION = new PermissionPO(id: 'syncSubscription', name: 'syncSubscription', group: PermissionGroup.ORGANIZATION, requiredOrganizationLevel: 2)
    static final List<PermissionPO> ALL_ORGANIZATION_PERMISSIONS = [
        CREATE_ORGANIZATION,
        EDIT_ORGANIZATION,
        GET_ORGANIZATION_BY_ID,
        DELETE_ORGANIZATION,
        ALL_ORGANIZATIONS,
        CREATE_SUBSCRIPTION,
        SYNC_SUBSCRIPTION,
    ]

    /**
     * ORGANIZATION PLUGINS
     */
    static final PermissionPO GET_ORGANIZATION_PLUGIN_BY_ID = new PermissionPO(id: 'getOrganizationPluginById', name: 'getOrganizationPluginById', group: PermissionGroup.ORGANIZATION_PLUGIN, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_ORGANIZATION_PLUGIN_BY_ID = new PermissionPO(id: 'editOrganizationPlugin', name: 'editOrganizationPlugin', group: PermissionGroup.ORGANIZATION_PLUGIN, requiredOrganizationLevel: 2)
    static final PermissionPO DELETE_ORGANIZATION_PLUGIN_BY_ID = new PermissionPO(id: 'deleteOrganizationPlugin', name: 'deleteOrganizationPlugin', group: PermissionGroup.ORGANIZATION_PLUGIN, requiredOrganizationLevel: 2)
    static final List<PermissionPO> ALL_ORGANIZATION_PLUGIN_PERMISSIONS = [
        GET_ORGANIZATION_PLUGIN_BY_ID,
        EDIT_ORGANIZATION_PLUGIN_BY_ID,
        DELETE_ORGANIZATION_PLUGIN_BY_ID,
    ]

    /**
     * CUSTOMERS
     */
    static final PermissionPO ALL_CUSTOMERS = new PermissionPO(id: 'allCustomers', name: 'allCustomers', group: PermissionGroup.CUSTOMER, requiredOrganizationLevel: 2)
    static final PermissionPO GET_CUSTOMER_BY_ID = new PermissionPO(id: 'getCustomerById', name: 'getCustomerById', group: PermissionGroup.CUSTOMER, requiredOrganizationLevel: 2)
    static final PermissionPO CREATE_CONTACT = new PermissionPO(id: 'createContact', name: 'createContact', group: PermissionGroup.CUSTOMER, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_CONTACT = new PermissionPO(id: 'editContact', name: 'editContact', group: PermissionGroup.CUSTOMER, requiredOrganizationLevel: 2)
    static final PermissionPO ARCHIVE_CONTACT = new PermissionPO(id: 'archiveContact', name: 'archiveContact', group: PermissionGroup.CUSTOMER, requiredOrganizationLevel: 2)
    static final PermissionPO CREATE_CUSTOMER = new PermissionPO(id: 'createCustomer', name: 'createCustomer', group: PermissionGroup.CUSTOMER, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_CUSTOMER = new PermissionPO(id: 'editCustomer', name: 'editCustomer', group: PermissionGroup.CUSTOMER, requiredOrganizationLevel: 2)
    static final PermissionPO ARCHIVE_CUSTOMER = new PermissionPO(id: 'archiveCustomer', name: 'archiveCustomer', group: PermissionGroup.CUSTOMER, requiredOrganizationLevel: 2)
    static final PermissionPO UNARCHIVE_CUSTOMER = new PermissionPO(id: 'unarchiveCustomer', name: 'unarchiveCustomer', group: PermissionGroup.CUSTOMER, requiredOrganizationLevel: 2)
    static final List<PermissionPO> ALL_CUSTOMER_PERMISSIONS = [
        ALL_CUSTOMERS,
        GET_CUSTOMER_BY_ID,
        CREATE_CONTACT,
        EDIT_CONTACT,
        ARCHIVE_CONTACT,
        CREATE_CUSTOMER,
        EDIT_CUSTOMER,
        ARCHIVE_CUSTOMER,
        UNARCHIVE_CUSTOMER,
    ]

    /**
     * ITEMS
     */
    static final PermissionPO CREATE_ITEM = new PermissionPO(id: 'createItem', name: 'createItem', group: PermissionGroup.ITEM, requiredOrganizationLevel: 1)
    static final PermissionPO EDIT_ITEM = new PermissionPO(id: 'editItem', name: 'editItem', group: PermissionGroup.ITEM, requiredOrganizationLevel: 1)
    static final PermissionPO ALL_ITEMS = new PermissionPO(id: 'allItems', name: 'allItems', group: PermissionGroup.ITEM, requiredOrganizationLevel: 1)
    static final PermissionPO GET_ITEM_BY_ID = new PermissionPO(id: 'getItemById', name: 'getItemById', group: PermissionGroup.ITEM, requiredOrganizationLevel: 1)
    static final List<PermissionPO> ALL_ITEM_PERMISSIONS = [
        CREATE_ITEM,
        EDIT_ITEM,
        ALL_ITEMS,
        GET_ITEM_BY_ID
    ]

    /**
     * ORGANIZATION ITEMS
     */
    static final PermissionPO CREATE_ORGANIZATION_ITEM = new PermissionPO(id: 'createOrganizationItem', name: 'createOrganizationItem', group: PermissionGroup.ORGANIZATION_ITEM, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_ORGANIZATION_ITEM = new PermissionPO(id: 'editOrganizationItem', name: 'editOrganizationItem', group: PermissionGroup.ORGANIZATION_ITEM, requiredOrganizationLevel: 2)
    static final PermissionPO ALL_ORGANIZATION_ITEMS = new PermissionPO(id: 'allOrganizationItems', name: 'allOrganizationItems', group: PermissionGroup.ORGANIZATION_ITEM, requiredOrganizationLevel: 2)
    static final PermissionPO SYNC_ORGANIZATION_ITEMS = new PermissionPO(id: 'syncOrganizationItems', name: 'syncOrganizationItems', group: PermissionGroup.ORGANIZATION_ITEM, requiredOrganizationLevel: 2)
    static final PermissionPO SYNC_ORGANIZATION_ITEM = new PermissionPO(id: 'syncOrganizationItem', name: 'syncOrganizationItem', group: PermissionGroup.ORGANIZATION_ITEM, requiredOrganizationLevel: 2)
    static final PermissionPO GET_ORGANIZATION_ITEM_BY_ID = new PermissionPO(id: 'getOrganizationItemById', name: 'getOrganizationItemById', group: PermissionGroup.ORGANIZATION_ITEM, requiredOrganizationLevel: 2)
    static final List<PermissionPO> ALL_ORGANIZATION_ITEM_PERMISSIONS = [
        CREATE_ORGANIZATION_ITEM,
        EDIT_ORGANIZATION_ITEM,
        ALL_ORGANIZATION_ITEMS,
        SYNC_ORGANIZATION_ITEMS,
        SYNC_ORGANIZATION_ITEM,
        GET_ORGANIZATION_ITEM_BY_ID
    ]


    /**
     * PRODUCT ORDERS
     */
    static final PermissionPO CREATE_PRODUCT_ORDER = new PermissionPO(id: 'createProductOrder', name: 'createProductOrder', group: PermissionGroup.PRODUCT_ORDER, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_PRODUCT_ORDER = new PermissionPO(id: 'editProductOrder', name: 'editProductOrder', group: PermissionGroup.PRODUCT_ORDER, requiredOrganizationLevel: 2)
    static final PermissionPO GET_PRODUCT_ORDER_BY_ID = new PermissionPO(id: 'getProductOrderById', name: 'getProductOrderById', group: PermissionGroup.PRODUCT_ORDER, requiredOrganizationLevel: 2)
    static final PermissionPO GET_DRAFT_PRODUCT_ORDER = new PermissionPO(id: 'getDraftProductOrder', name: 'getDraftProductOrder', group: PermissionGroup.PRODUCT_ORDER, requiredOrganizationLevel: 2)
    static final PermissionPO ADD_TO_CART = new PermissionPO(id: 'addToCart', name: 'addToCart', group: PermissionGroup.PRODUCT_ORDER, requiredOrganizationLevel: 2)
    static final PermissionPO ALL_PRODUCT_ORDERS = new PermissionPO(id: 'allProductOrders', name: 'allProductOrders', group: PermissionGroup.PRODUCT_ORDER, requiredOrganizationLevel: 2)
    static final List<PermissionPO> ALL_PRODUCT_ORDER_PERMISSIONS = [
        CREATE_PRODUCT_ORDER,
        EDIT_PRODUCT_ORDER,
        GET_PRODUCT_ORDER_BY_ID,
        GET_DRAFT_PRODUCT_ORDER,
        ADD_TO_CART,
        ALL_PRODUCT_ORDERS
    ]

    /**
     * ROLE_MANAGEMENT
     */
    static final PermissionPO CREATE_ROLE = new PermissionPO(id: 'createRole', name: 'createRole', group: PermissionGroup.ROLE_MANAGEMENT, requiredOrganizationLevel: 2)
    static final PermissionPO EDIT_ROLE = new PermissionPO(id: 'editRole', name: 'editRole', group: PermissionGroup.ROLE_MANAGEMENT, requiredOrganizationLevel: 2)
    static final PermissionPO ALL_ROLES = new PermissionPO(id: 'allRoles', name: 'allRoles', group: PermissionGroup.ROLE_MANAGEMENT, requiredOrganizationLevel: 2)
    static final PermissionPO GET_ROLE_BY_ID = new PermissionPO(id: 'getRoleById', name: 'getRoleById', group: PermissionGroup.ROLE_MANAGEMENT, requiredOrganizationLevel: 2)
    static final PermissionPO ALL_ROLE_PERMISSIONS = new PermissionPO(id: 'allPermissions', name: 'allPermissions', group: PermissionGroup.ROLE_MANAGEMENT, requiredOrganizationLevel: 2)
    static final List<PermissionPO> ALL_ROLE_MANAGEMENT_PERMISSIONS = [
        CREATE_ROLE,
        EDIT_ROLE,
        ALL_ROLES,
        GET_ROLE_BY_ID,
        ALL_ROLE_PERMISSIONS
    ]

    /**
     * MISC; generally things that shouldn't require authentication/authorization
     * (except for UPDATE_PASSWORD, which should require a token. UPDATE_PASSWORD is part of the password recovery workflow).
     */
    static final PermissionPO SEARCH = new PermissionPO(id: 'search', name: 'search', group: PermissionGroup.ANY, requiredOrganizationLevel: 2)
    static final PermissionPO LOGIN = new PermissionPO(id: 'login', name: 'login', group: PermissionGroup.ANY, requiredOrganizationLevel: 2)
    static final PermissionPO LOGOUT = new PermissionPO(id: 'logout', name: 'logout', group: PermissionGroup.ANY, requiredOrganizationLevel: 2)
    static final PermissionPO SEND_PASSWORD_RESET_CODE = new PermissionPO(id: 'sendPasswordResetCode', name: 'sendPasswordResetCode', group: PermissionGroup.ANY, requiredOrganizationLevel: 2)
    static final PermissionPO VERIFY_PASSWORD_RESET_CODE = new PermissionPO(id: 'verifyPasswordResetCode', name: 'verifyPasswordResetCode', group: PermissionGroup.ANY, requiredOrganizationLevel: 2)
    static final PermissionPO UPDATE_PASSWORD = new PermissionPO(id: 'updatePassword', name: 'updatePassword', group: PermissionGroup.ANY, requiredOrganizationLevel: 2)
    static final PermissionPO ACCEPT_QUOTE = new PermissionPO(id: 'acceptQuote', name: 'acceptQuote', group: PermissionGroup.ANY, requiredOrganizationLevel: 2)
    static final PermissionPO CREATE_QUOTE_REQUEST = new PermissionPO(id: 'createQuoteRequest', name: 'createQuoteRequest', group: PermissionGroup.ANY, requiredOrganizationLevel: 2)
    static final PermissionPO GET_ALL_SUBSCRIPTION_OPTIONS = new PermissionPO(id: 'allSubscriptionOptions', name: 'allSubscriptionOptions', group: PermissionGroup.ANY, requiredOrganizationLevel: 2)
    static final PermissionPO ARCHIVE_ATTACHMENT = new PermissionPO(id: 'archiveAttachment', name: 'archiveAttachment', group: PermissionGroup.ANY, requiredOrganizationLevel: 2)
    static final List<PermissionPO> ALL_MISC_PERMISSIONS = [
        SEARCH,
        LOGIN,
        LOGOUT,
        SEND_PASSWORD_RESET_CODE,
        VERIFY_PASSWORD_RESET_CODE,
        UPDATE_PASSWORD, // this operation is intended only for a user to reset their own password (no admin should ever mess with other users passwords)
        GET_QUOTE_BY_ID, // Quotes generally need to be publicly accessible (provided that someone knows the quote ID and the URL). Exceptions are made considering the state of the Quote. See QuoteModule.
        ACCEPT_QUOTE, // acceptQuote is open to the public.
        CREATE_QUOTE_REQUEST, // open to the public
        GET_INVOICE_BY_ID, // Invoices generally need to be publicly accessible (provided that someone knows the invoice ID and the URL). Exceptions are made considering the state of the Quote. See InvoiceModule.
        GET_ALL_SUBSCRIPTION_OPTIONS, // anyone can view our tier plans & prices
        GET_MY_PROFILE, // Anyone can view/edit their own profile
        EDIT_MY_PROFILE, // Anyone can view/edit their own profile
        ARCHIVE_ATTACHMENT
    ]

    static final List<PermissionPO> ALL_PERMISSIONS_FOR_NON_OWNER_INACTIVE_ORGS = [
        LOGIN,
        LOGOUT,
        VERIFY_PASSWORD_RESET_CODE,
        UPDATE_PASSWORD,  // this operation is intended only for a user to reset their own password (no admin should ever mess with other users passwords)
    ]

    static final List<PermissionPO> ALL_PERMISSIONS_FOR_OWNER_INACTIVE_ORGS = [
        LOGIN,
        LOGOUT,
        VERIFY_PASSWORD_RESET_CODE,
        UPDATE_PASSWORD,  // this operation is intended only for a user to reset their own password (no admin should ever mess with other users passwords)
        GET_ALL_SUBSCRIPTION_OPTIONS,
        CREATE_SUBSCRIPTION,
        SYNC_SUBSCRIPTION
    ]

    static boolean permissionIdExists(String permissionId) {
        return permissionIdsExist([permissionId])
    }

    static boolean permissionIdsExist(List<String> permissionIds) {
        return ALL.collect { it.id }.sort().containsAll(permissionIds)
    }

    static boolean permissionNameExists(String permissionName) {
        return permissionNamesExist([permissionName])
    }

    static boolean permissionNamesExist(List<String> permissionNames) {
        return ALL.collect { it.name }.sort().containsAll(permissionNames)
    }

    protected static final List<PermissionPO> ALL = [
        ALL_CHECKLIST_PERMISSIONS,
        ALL_CREW_PERMISSIONS,
        ALL_CUSTOMER_PERMISSIONS,
        ALL_INVOICE_PERMISSIONS,
        ALL_ITEM_PERMISSIONS,
        ALL_JOB_ASSIGNMENT_PERMISSIONS,
        ALL_JOB_CATEGORY_PERMISSIONS,
        ALL_JOB_PERMISSIONS,
        ALL_JOB_STATUS_PERMISSIONS,
        ALL_JOB_WORKFLOW_PERMISSIONS,
        ALL_MISC_PERMISSIONS,
        ALL_ORGANIZATION_ITEM_PERMISSIONS,
        ALL_ORGANIZATION_PERMISSIONS,
        ALL_ORGANIZATION_PLUGIN_PERMISSIONS,
        ALL_PAYMENT_PERMISSIONS,
        ALL_PRODUCT_ORDER_PERMISSIONS,
        ALL_QUOTE_PERMISSIONS,
        ALL_QUOTE_REQUEST_PERMISSIONS,
        ALL_REGION_PERMISSIONS,
        ALL_ROLE_MANAGEMENT_PERMISSIONS,
        ALL_TASK_PERMISSIONS,
        ALL_TAX_RATE_GROUP_PERMISSIONS,
        ALL_USER_PERMISSIONS,
        ALL_WORK_ORDER_PERMISSIONS
    ].flatten().unique() as List<PermissionPO>

    static List<PermissionPO> getPermissionsByLevel(int level) {
        return ALL.findAll { it.requiredOrganizationLevel >= level }
    }

}
