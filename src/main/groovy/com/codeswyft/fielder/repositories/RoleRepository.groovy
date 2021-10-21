package com.codeswyft.fielder.repositories

import com.codeswyft.fielder.domain.RolePO
import com.codeswyft.fielder.model.Role
import groovy.transform.CompileStatic

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@CompileStatic
interface RoleRepository extends BaseRepository<RolePO> {

    RolePO create(@NotNull Role role)

    RolePO update(@NotNull Role role)

    List<RolePO> findAllByUserId(@NotBlank String userId)

}
