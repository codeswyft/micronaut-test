package com.codeswyft.fielder.repositories

import com.codeswyft.fielder.domain.PermissionPO
import com.codeswyft.fielder.model.Permission
import groovy.transform.CompileStatic

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@CompileStatic
interface PermissionRepository extends BaseRepository<PermissionPO> {

    PermissionPO create(@NotNull Permission permission)

    List<PermissionPO> findAllByRoleId(@NotBlank String roleId)

}
