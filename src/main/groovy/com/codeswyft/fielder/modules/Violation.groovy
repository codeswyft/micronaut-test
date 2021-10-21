package com.codeswyft.fielder.modules

import groovy.transform.CompileStatic

@CompileStatic
enum Violation {

    CUSTOMER_LOCKED_JOB_HAS_TXN('customer-locked.job-has-txn'),
    DELETE_NOT_ALLOWED('delete.not-allowed'),
    DUPLICATE_COMPONENT_ITEM('duplicate.component.item.id'),
    EMAIL_SEND_ERROR('email.send'), // general way to indicate an error occurred while trying to send an email
    GROUP_ITEM_COMPONENTS_EMPTY('group-item.components.empty'),
    GROUP_ITEM_COMPONENTS_INVALID_ITEM('group-item.components.invalid-item'),   // component item could not be found
    GROUP_ITEM_COMPONENTS_INVALID_TYPE('group-item.components.invalid-type'),   // only PRODUCT items may be components of a GROUP item
    INVALID_ARGUMENT('invalid.argument.general'),
    INVALID_ARGUMENT_ID_REQUIRED('invalid.argument.id.required'),
    INVALID_ARGUMENT_ID_STANDARD('invalid.argument.id.standard'),
    INVALID_ARGUMENT_NAME_REQUIRED('invalid.argument.name.required'),
    INVALID_ARGUMENT_NAME_UNIQUE('invalid.argument.name.unique'),
    INVALID_ARGUMENT_OWNER_INVALID('invalid.argument.owner.invalid'),
    INVALID_ARGUMENT_OWNER_REQUIRED('invalid.argument.owner.required'),
    INVALID_ARGUMENT_PERMISSIONS_IDS_EMPTY('invalid.argument.permissionIds.empty'),
    INVALID_ARGUMENT_PERMISSIONS_IDS_INVALID_ID('invalid.argument.permissionIds.invalid-id'),
    INVALID_ARGUMENT_USERNAME_UNIQUE('invalid.argument.username.unique'),
    INVALID_ASSIGNEE('invalid.assignee.id'),
    INVALID_ASSIGNEE_DUPLICATE('invalid.assignee.duplicate'),
    INVALID_ASSIGNEE_ORGANIZATION('invalid.assignee.organization'),
    INVALID_ATTACHMENT('invalid.attachment'),
    INVALID_CHECKLIST('invalid.checklist.id'),
    INVALID_CHECKLIST_LINE_ITEM_ID('invalid.checklist.line-item.id'),
    INVALID_CHECKLIST_TEMPLATE('invalid.checklist-template.id'),
    INVALID_CODE('invalid.code'),
    INVALID_CONTACT('invalid.contact.id'),                              // general - no particular reason given; usually can't be found by id
    INVALID_CONTACT_ARCHIVED('invalid.contact.archived'),               // the contact is invalid because it has been archived.
    INVALID_CONTACT_FOR_BILLING('invalid.contact.for.billing'),         // the contact cannot be used as a billing contact
    INVALID_CONTACT_FOR_CUSTOMER('invalid.contact.for.customer'),       // the contact cannot be used - it is not tied to the same customer as the job's customer
    INVALID_CONTACT_FOR_JOB_SITE('invalid.contact.for.job-site'),       // the contact cannot be used as a job site contact
    INVALID_CONTACT_HOMEOWNER('invalid.contact.homeowner'),
    INVALID_CREDENTIALS('invalid.credentials'),
    INVALID_CREW('invalid.crew.id'),
    INVALID_CUSTOMER('invalid.customer.id'),
    INVALID_CUSTOMER_ARCHIVED('invalid.customer.archived'),
    INVALID_DATES('invalid.dates'),
    INVALID_EMAIL_ADDRESS('invalid.email'),
    INVALID_ID('invalid.id'),
    INVALID_INVENTORY_ITEMS('invalid.inventory-items'),
    INVALID_INVOICE('invalid.invoice.id'),
    INVALID_INVOICE_NO_LINE_ITEMS('invalid.invoice.no-line-items'),
    INVALID_INVOICE_STATUS('invalid.invoice.status'),
    INVALID_ITEM('invalid.item.id'),
    INVALID_JOB('invalid.job.id'),
    INVALID_JOB_ASSIGNMENT('invalid.job-assignment.id'),
    INVALID_JOB_BOARD('invalid.job.board'),
    INVALID_JOB_CATEGORY('invalid.job-category.id'),
    INVALID_JOB_NOTE('invalid.job.note'),
    INVALID_JOB_STATUS('invalid.job.status.id'),
    INVALID_JOB_WORKFLOW('invalid.job.workflow'),
    INVALID_JOB_WORKFLOW_STEP('invalid.job-workflow-step'),
    INVALID_JOB_WORKFLOW_STEP_TRANSITION('invalid.job-workflow-step-transition'),
    INVALID_LINE_ITEM('invalid.line-item.id'),
    INVALID_LINE_ITEM_DUPLICATE('invalid.line-item.duplicate'),
    INVALID_MEMBER('invalid.member.id'),
    INVALID_MEMBER_ORGANIZATION('invalid.member.organization'),
    INVALID_MESSAGE_CHANNEL('invalid.message.channel'),
    INVALID_ORGANIZATION('invalid.organization.id'),
    INVALID_ORGANIZATION_ITEM('invalid.organization-item.id'),
    INVALID_ORGANIZATION_PLUGIN('invalid.organization-plugin'),
    INVALID_PAYMENT('invalid.payment.id'),
    INVALID_PHONE('invalid.phone'),
    INVALID_PLUGIN('invalid.plugin'),
    INVALID_PRODUCT_ORDER('invalid.product-order.id'),
    INVALID_QUOTE('invalid.quote.id'),
    INVALID_QUOTE_REQUEST('invalid.quote-request.id'),
    INVALID_QUOTE_REQUEST_STATUS('invalid.quote-request.status'),
    INVALID_QUOTE_STATUS('invalid.quote.status'),
    INVALID_QUOTE_TEMPLATE('invalid.quote-template'),
    INVALID_RECAPTCHA_RESPONSE('invalid.recaptcha'),
    INVALID_REGION('invalid.region.id'),
    INVALID_REGION_ALREADY_EXISTS('invalid.region.already-exists'),
    INVALID_ROLE('invalid.role.id'),
    INVALID_STATE_TRANSITION('invalid.state.transition'),
    INVALID_SUBSCRIPTION('invalid.subscription'),
    INVALID_TASK('invalid.task.id'),
    INVALID_TOKEN('invalid.token'),
    INVALID_USER('invalid.user.id'),
    INVALID_USER_STATUS('invalid.user.status'),
    INVALID_WORK_ORDER('invalid.work-order'),
    INVALID_WORK_ORDER_TEMPLATE('invalid.work-order-template'),
    INVENTORY_NOT_EDITABLE('inventory.not-editable'),
    INVOICE_ALREADY_SENT('invoice.already-sent'),
    INVOICE_NOT_APPROVED('invoice.not-approved'),
    JOB_STATUS_NAME_UNIQUE('job-status.name.unique'),
    JOB_WORKFLOW_ARCHIVED('job-workflow.archived'),                   // can't add a job to an archived workflow
    JOB_WORKFLOW_LOCKED_PUBLISHED('job-workflow.locked-published'),   // a workflow cannot be edited after it is published
    JOB_WORKFLOW_PARENT_ARCHIVED('job-workflow.parent.archived'),     // trying to create a workflow with an archived parent
    JOB_WORKFLOW_PARENT_REQUIRED('job-workflow.parent.required'),
    JOB_WORKFLOW_STEP_INELIGIBLE_INITIAL('job-workflow.step.ineligible-initial'),           // thrown when trying to set an initial step to one that is ineligible for that status
    JOB_WORKFLOW_STEP_HAS_JOBS('job-workflow.step.has-jobs'),                               // thrown when trying to delete a workflow step that has jobs
    JOB_WORKFLOW_STEP_IS_INITIAL('job-workflow.step.is-initial'),                           // thrown when trying to delete a workflow step that is the workflow's designated initial step
    JOB_WORKFLOW_STEP_TRANSITION_NOT_ALLOWED('job-workflow.step-transition.not-allowed'),   // thrown when trying to transition a job in a way that is not allowed by the workflow
    NON_ACTIVE_ORGANIZATION_CHAIN('nonactive.organization-chain'),
    NOT_AUTHORIZED('not-authorized'),
    ONE_CHECKLIST_PER_JOB('one-checklist-per-job'),
    OPERATION_NOT_SUPPORTED('invalid.operation'),
    PASSWORD_STRENGTH('invalid.password.weak'),
    PAYMENT_INVOICE_MISMATCH('invalid.payment.invoice-mismatch'),
    PAYMENT_NOT_ACCEPTED('payment.not-accepted'),
    PLUGIN_QBO_MULTIPLE_TAX_RATES_PROHIBITED('plugin.qbo.multiple-tax-rates-prohibited'),
    REVOKE_TOKEN_FAILURE('failed.revoke.token'),
    SUBSCRIPTION_CARD_FAILURE('subscription.card.failure.decline'),
    SUBSCRIPTION_STRIPE_FAILURE('subscription.stripe.failure'),
    TAX_RATE_GROUP_LOCKED('tax-rate-group.locked'),
    THERE_CAN_ONLY_BE_ONE('invalid.level.org'),
    TOKEN_EXPIRED('token.expired'),

    private final String violationKey

    Violation(String violationKey) {
        this.violationKey = violationKey
    }

    String getViolationKey() {
        return violationKey
    }

    String toString() {
        return violationKey
    }
}
