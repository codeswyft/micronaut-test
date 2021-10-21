package com.codeswyft.fielder.repositories

import com.codeswyft.fielder.domain.RolePO
import com.codeswyft.fielder.domain.UserPO
import com.codeswyft.fielder.model.User
import com.codeswyft.fielder.model.UserStatus
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.micronaut.transaction.annotation.ReadOnly

import javax.inject.Singleton

import javax.persistence.EntityManager
import javax.persistence.TypedQuery
import javax.transaction.Transactional
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import java.time.LocalDateTime

@Slf4j
@Singleton
@CompileStatic
class UserRepositoryImpl extends BaseRepositoryImpl<UserPO> implements UserRepository {

    private final RoleRepository roleRepository

    UserRepositoryImpl(EntityManager entityManager,
                       RoleRepository roleRepository) {
        super(entityManager, UserPO)
        this.roleRepository = roleRepository
    }

    @Override
    @Transactional
    UserPO create(@NotNull User user, @NotBlank String hashedPassword) {
        UserPO userPO = new UserPO()
        userPO.with {
            firstName = user.firstName
            lastName = user.lastName
            email = user.email
            jobTitle = user.jobTitle
            mobilePhoneNumber = user.mobilePhoneNumber
            status = user.status
            lastLogin = user.lastLogin
            organizationId = user.organizationId
            createdById = user.createdById
            timeZone = user.timeZone
            password = hashedPassword
        }

        List<RolePO> userRolePOs = roleRepository.findByIds(user.roleIds)
        if (userRolePOs.empty) {
            new RuntimeException("Cannot create a User without any Roles")
        }

        userPO.roles = userRolePOs as Set<RolePO>

        if (user.id) {
            userPO.id = user.id
        }

        return create(userPO)
    }

    @Override
    @ReadOnly
    Optional<UserPO> findByEmail(@NotBlank String email) {
        String queryStr = 'SELECT u FROM UserPO as u where u.email = :email'

        TypedQuery<UserPO> query = entityManager.createQuery(queryStr, UserPO)
        query.setParameter('email', email)

        return Optional.ofNullable(query.getResultList()[0])
    }

    @Override
    @Transactional
    UserPO update(@NotNull User user) {
        UserPO userPO = findById(user.id).orElseThrow {
            new RuntimeException("Cannot find User with id ${user.id}")
        }

        userPO.with {
            firstName = user.firstName
            lastName = user.lastName
            status = user.status
            email = user.email
            jobTitle = user.jobTitle
            mobilePhoneNumber = user.mobilePhoneNumber
            organizationId = user.organizationId
            updatedById = user.updatedById
            timeZone = user.timeZone
            roles = userPO.roles
        }

        List<RolePO> rolePOs = roleRepository.findByIds(user.roleIds)
        userPO.roles = rolePOs as Set<RolePO>

        return update(userPO)
    }

    @Override
    @Transactional
    void updateLastLogin(@NotBlank String userId) {
        UserPO userPO = findById(userId).orElseThrow {
            new RuntimeException("Cannot find User with id ${userId}")
        }
        userPO.lastLogin = LocalDateTime.now()
        update(userPO)
    }

    @Override
    @Transactional
    void updatePassword(@NotNull UserPO userPO, @NotBlank String password) {
        userPO.password = password
        userPO.status = UserStatus.ACTIVE
        update(userPO)
    }

    @Override
    @Transactional
    UserPO lockForPasswordChange(@NotBlank String userId) {
        UserPO userPO = findById(userId).orElseThrow {
            new RuntimeException("Cannot find User with id ${userId}")
        }
        userPO.status = UserStatus.LOCKED_NEEDS_PASSWORD_CHANGE
        userPO.password = UUID.randomUUID().toString()
        return update(userPO)
    }

    @Override
    // Note: I needed to add this because when I updated a UserPO directly,
    // and added a RolePO to the roles Set, JPA was unable to save it properly (bug?)
    UserPO update(@NotNull UserPO entity) {
        List<RolePO> rolePOs = roleRepository.findByIds(entity.roles.collect { it.id })
        entity.roles = rolePOs as Set<RolePO>
        return super.update(entity) as UserPO
    }

}
