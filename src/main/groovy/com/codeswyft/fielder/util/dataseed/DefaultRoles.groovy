package com.codeswyft.fielder.util.dataseed

import com.codeswyft.fielder.domain.PermissionPO
import com.codeswyft.fielder.domain.RolePO
import groovy.transform.CompileStatic

/**
 * This class provide definitions for the standard, read-only set of Roles and their associated Permissions.
 * These Role definitions will be assigned to every Organization.
 */
@CompileStatic
class DefaultRoles {

    static final RolePO ADMIN_LEVEL_0 = getLevelRole(0, "A master role. Someone with this role can perform tasks on behalf of any organization in the system.")
    static final RolePO ADMIN_LEVEL_1 = getLevelRole(1, "A admin role. Someone with this role can perform tasks on behalf of their organization as well as all child organizations.")
    static final RolePO ADMIN_LEVEL_2 = getLevelRole(2, "A standard role. Someone with this role can perform tasks on behalf of their organization.")
    static final RolePO DISPATCHER = getDispatcherRole()
    static final RolePO FIELD_TECH = getFieldTechRole()

    static RolePO getLevelRole(int level, String description) {
        String roleName = 'ADMIN_LEVEL_' + level
        return setRequiredOrganizationLevel(new RolePO(
            id: roleName,
            name: roleName,
            description: description,
            isStandard: true,
            permissions: DefaultPermissions.getPermissionsByLevel(level) as Set<PermissionPO>,
        ))
    }

    static RolePO getDispatcherRole() {
        String roleName = 'DISPATCHER'
        return setRequiredOrganizationLevel(new RolePO(
            id: roleName,
            name: roleName,
            description: "Someone whose primary responsibility is to schedule & assign Jobs",
            isStandard: true,
            permissions: [
                DefaultPermissions.ALL_MISC_PERMISSIONS,
                DefaultPermissions.ALL_JOB_ASSIGNMENT_PERMISSIONS,
                DefaultPermissions.ALL_JOBS,
                DefaultPermissions.ALL_JOB_STATUSES,
                DefaultPermissions.GET_JOB_STATUS_BY_ID,
                DefaultPermissions.ALL_JOB_CATEGORIES,
                DefaultPermissions.GET_JOB_CATEGORY_BY_ID,
                DefaultPermissions.ALL_TASK_PERMISSIONS,
                DefaultPermissions.GET_JOB_BY_ID,
                DefaultPermissions.EDIT_JOB,
                DefaultPermissions.CREATE_JOB_NOTE,
                DefaultPermissions.EDIT_JOB_NOTE,
                DefaultPermissions.GET_ORGANIZATION_BY_ID,
                DefaultPermissions.ALL_USERS,
                DefaultPermissions.EDIT_CHECKLIST,
                DefaultPermissions.GET_CHECKLIST,
                DefaultPermissions.CREATE_CHECKLIST_LINE_ITEM_NOTE,
                DefaultPermissions.ALL_TAX_RATE_GROUPS,
                DefaultPermissions.ALL_CREW_PERMISSIONS,
                DefaultPermissions.EDIT_WORK_ORDER,
                DefaultPermissions.GET_WORK_ORDER_BY_ID,
            ].flatten() as Set<PermissionPO>
        ))
    }

    static RolePO getFieldTechRole() {
        String roleName = 'FIELD_TECH'
        return setRequiredOrganizationLevel(new RolePO(
            id: roleName,
            name: roleName,
            description: "Someone whose primary responsibility is providing service to customers directly.",
            isStandard: true,
            permissions: [
                DefaultPermissions.ALL_MISC_PERMISSIONS,
                DefaultPermissions.GET_JOB_ASSIGNMENT_BY_ID,
                DefaultPermissions.ALL_JOB_ASSIGNMENTS,
                DefaultPermissions.ALL_JOB_STATUSES,
                DefaultPermissions.GET_JOB_STATUS_BY_ID,
                DefaultPermissions.ALL_JOB_CATEGORIES,
                DefaultPermissions.GET_JOB_CATEGORY_BY_ID,
                DefaultPermissions.ALL_TASK_PERMISSIONS,
                DefaultPermissions.GET_JOB_BY_ID,
                DefaultPermissions.EDIT_JOB,
                DefaultPermissions.CREATE_JOB_NOTE,
                DefaultPermissions.EDIT_JOB_NOTE,
                DefaultPermissions.GET_ORGANIZATION_BY_ID,
                DefaultPermissions.CREATE_USER_LOCATION,
                DefaultPermissions.ALL_USERS,  // so they can view other installers schedules on the mobile app
                DefaultPermissions.EDIT_CHECKLIST,
                DefaultPermissions.GET_CHECKLIST,
                DefaultPermissions.CREATE_CHECKLIST_LINE_ITEM_NOTE,
                DefaultPermissions.ALL_TAX_RATE_GROUPS,
                DefaultPermissions.EDIT_WORK_ORDER,
                DefaultPermissions.GET_WORK_ORDER_BY_ID,
            ].flatten() as Set<PermissionPO>
        ))
    }

    static RolePO setRequiredOrganizationLevel(RolePO rolePO) {
        rolePO.requiredOrganizationLevel = rolePO.permissions.collect { it.requiredOrganizationLevel }.sort().first()
        return rolePO
    }

    static boolean roleIdExists(String roleId) {
        return roleIdsExist([roleId])
    }

    static boolean roleIdsExist(List<String> roleIds) {
        return ALL.collect { it.id }.sort().containsAll(roleIds)
    }

    static boolean roleNameExists(String roleName) {
        return roleNamesExist([roleName])
    }

    static boolean roleNamesExist(List<String> roleNames) {
        return ALL.collect { it.name }.sort().containsAll(roleNames)
    }

    // Note: This should NOT be used outside this class besides seeders!
    protected static final List<RolePO> ALL = [
        ADMIN_LEVEL_0,
        ADMIN_LEVEL_1,
        ADMIN_LEVEL_2,
        DISPATCHER,
        FIELD_TECH
    ].flatten().unique() as List<RolePO>

    // Note: Small gotcha. Keep in mind that a canned role such as ADMIN_LEVEL_0 could be returned given an input param
    // of level = 1 if there are no permissions in the system that have a requiredOrganizationLevel = 0
    static List<RolePO> getRolesByLevel(int level) {
        return ALL.findAll { it.requiredOrganizationLevel >= level }
    }

}
