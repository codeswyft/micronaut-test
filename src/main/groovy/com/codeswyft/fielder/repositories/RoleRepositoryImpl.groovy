package com.codeswyft.fielder.repositories

import com.codeswyft.fielder.domain.PermissionPO
import com.codeswyft.fielder.domain.RolePO
import com.codeswyft.fielder.model.Role
import groovy.transform.CompileStatic
import io.micronaut.transaction.annotation.ReadOnly

import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@CompileStatic
@Singleton
class RoleRepositoryImpl extends BaseRepositoryImpl<RolePO> implements RoleRepository {

    private PermissionRepository permissionRepository

    RoleRepositoryImpl(EntityManager entityManager,
                       PermissionRepository permissionRepository) {
        super(entityManager, RolePO)
        this.permissionRepository = permissionRepository
    }

    @Override
    @Transactional
    RolePO create(@NotNull Role role) {
        //---------------------------------------------------------------------------------------------
        // NOTE: we are ignoring role.requiredOrganizationLevel (and calculating and setting it below)
        //---------------------------------------------------------------------------------------------

        RolePO rolePO = new RolePO()
        rolePO.with {
            name = role.name
            description = role.description
            organizationId = role.organizationId
            createdById = role.createdById
        }

        // A Role might already have an ID assigned if it is a "standard"/"default" Role.
        // See DefaultRoles for definitions and RoleSeeder.saveDefaultRoles() for more info.
        // We're pre-populating IDs ahead of time for standard Roles just to make testing easier.
        if (role.isStandard) {
            rolePO.id = role.id
            rolePO.isStandard = true
        }

        // we already validated these permission ids in the module class
        List<PermissionPO> permissionPOs = permissionRepository.findByIds(role.permissionIds)
        rolePO.permissions = permissionPOs as Set<PermissionPO>

        // calculate the required organization level based on the permissions assigned
        rolePO.requiredOrganizationLevel = permissionPOs.collect { it.requiredOrganizationLevel }.sort().first()
        return create(rolePO)
    }

    @Override
    @Transactional
    RolePO update(@NotNull Role role) {
        //---------------------------------------------------------------------------------------------
        // NOTE: we are ignoring role.requiredOrganizationLevel (and calculating and setting it below)
        //---------------------------------------------------------------------------------------------

        RolePO rolePO = findById(role.id).orElseThrow { new RuntimeException("Cannot find Role with ID ${role.id}") }
        if (rolePO.isStandard) {
            throw new RuntimeException("Standard roles are not editable")
        }

        rolePO.with {
            // Note: don't update the organizationId
            name = role.name
            requiredOrganizationLevel = role.requiredOrganizationLevel
            description = role.description
            updatedById = role.updatedById
        }

        // we already validated these permission ids in the module class
        List<PermissionPO> permissionPOs = permissionRepository.findByIds(role.permissionIds)
        rolePO.permissions = permissionPOs as Set<PermissionPO>
        return update(rolePO)
    }

    /**
     * Find all Roles for the User represented by the given userId.
     * @param userId
     * @return
     */
    @Override
    @ReadOnly
    List<RolePO> findAllByUserId(@NotBlank String userId) {
        String queryStr = '''
        SELECT r.*
        FROM role r JOIN user_role u ON r.id = u.role_id
        WHERE u.user_id = :userId
        '''

        List<RolePO> roles =
            entityManager.createNativeQuery(queryStr, RolePO)
                .setParameter('userId', userId)
                .getResultList()

        return roles
    }

}
