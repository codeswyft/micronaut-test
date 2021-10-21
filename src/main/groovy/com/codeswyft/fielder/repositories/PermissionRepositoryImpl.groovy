package com.codeswyft.fielder.repositories

import com.codeswyft.fielder.domain.PermissionPO
import com.codeswyft.fielder.domain.RolePO
import com.codeswyft.fielder.model.Permission
import com.codeswyft.fielder.util.CommonKeys
import groovy.transform.CompileStatic
import io.micronaut.transaction.annotation.ReadOnly

import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@CompileStatic
@Singleton
class PermissionRepositoryImpl extends BaseRepositoryImpl<PermissionPO> implements PermissionRepository {

    PermissionRepositoryImpl(EntityManager entityManager) {
        super(entityManager, PermissionPO)
    }

    @Override
    @Transactional
    PermissionPO create(@NotNull Permission permission) {
        PermissionPO permissionPO = new PermissionPO()
        permissionPO.with {
            it.id = permission.id
            it.name = permission.name
            it.group = permission.group
            it.requiredOrganizationLevel = permission.requiredOrganizationLevel
            it.createdById = CommonKeys.SYSTEM_USER // Permissions are pre-defined, and can only be created by system
        }
        return create(permissionPO)
    }

    @Override
    @ReadOnly
    List<PermissionPO> findAllByRoleId(@NotBlank String roleId) {
        RolePO rolePO = Optional.ofNullable(entityManager.find(RolePO.class, roleId)).orElseThrow {
            new RuntimeException("Role with id ${roleId} not found")
        }
        return rolePO.permissions as List<PermissionPO>
    }

}
