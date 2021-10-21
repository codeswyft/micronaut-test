package com.codeswyft.fielder.modules

import com.codeswyft.fielder.domain.RolePO
import com.codeswyft.fielder.domain.UserPO
import com.codeswyft.fielder.graphql.input.LoginInput
import com.codeswyft.fielder.graphql.payload.LoginPayload
import com.codeswyft.fielder.model.AuthenticationContext
import com.codeswyft.fielder.model.Role
import com.codeswyft.fielder.model.User
import com.codeswyft.fielder.model.UserStatus
import com.codeswyft.fielder.repositories.RoleRepository
import com.codeswyft.fielder.repositories.UserRepository
import com.codeswyft.fielder.util.ValidationUtil
import com.codeswyft.fielder.util.dataseed.DefaultPermissions
import com.fasterxml.jackson.databind.ObjectMapper
import graphql.schema.DataFetchingEnvironment
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.micronaut.context.annotation.Value
import org.slf4j.MDC

import javax.inject.Singleton
import javax.transaction.Transactional
import java.time.Duration
import java.time.LocalDateTime

@Slf4j
@Singleton
@CompileStatic
class AuthenticationModule extends BaseModule {

    UserRepository userRepository
    RoleRepository roleRepository
    ObjectMapper objectMapper
    String salt
    Duration passwordResetTokenExpiration
    Duration userInvitationExpiration

    static final String CLAIM_KEY_ORGANIZATION_ID = 'organizationId'
    static final String CLAIM_KEY_ORGANIZATION_LEVEL = 'organizationLevel'
    static final String CLAIM_KEY_ORGANIZATION_STATUS = 'organizationStatus'
    static final String CLAIM_KEY_OWNER = 'owner'
    static final String CLAIM_KEY_PARENT_ORGANIZATION_ID = 'parentOrganizationId'
    static final String CLAIM_KEY_ROLE_NAMES = 'roles'
    static final String CLAIM_KEY_OPERATIONS = 'ops'

    AuthenticationModule(UserRepository userRepository,
                         RoleRepository roleRepository,
                         ObjectMapper objectMapper,
                         @Value('${app.password-salt}') String salt,
                         @Value('${app.password-reset.expiration}') Duration passwordResetTokenExpiration,
                         @Value('${app.user-invitation.expiration}') Duration userInvitationExpiration) {
        this.userRepository = userRepository
        this.roleRepository = roleRepository
        this.objectMapper = objectMapper
        this.salt = salt
        this.passwordResetTokenExpiration = passwordResetTokenExpiration
        this.userInvitationExpiration = userInvitationExpiration
    }

    private String buildAllowedOperationsString(UserPO userPO) {
        return userPO.roles.collect { it.permissions.collect { it.name } }.flatten().unique().sort().join(',')
    }

    @Transactional
    LoginPayload authenticateUser(LoginInput loginRequest, DataFetchingEnvironment env) {
        MDC.put("username", loginRequest.username)
        log.debug('Authenticating user using supplied credentials..')

        // Retrieve user
        UserPO userPO = userRepository.findByEmail(loginRequest.username)
            .map { userPO -> Optional.ofNullable(com.codeswyft.fielder.util.PasswordUtil.check(loginRequest.password, salt, userPO.password) ? userPO : null) }
            .filter({ userPO -> userPO.isPresent() })
            .map { userPO -> userPO.get() }
            .orElseThrow { ValidationUtil.createValidationError('invalid creds', Violation.INVALID_CREDENTIALS) }

        if (userPO.status != UserStatus.ACTIVE) {
            ValidationUtil.throwValidationError('user is not in active status', Violation.INVALID_USER_STATUS)
        }

        // Make sure to update the User's lastLogin
        userRepository.updateLastLogin(userPO.id)
        User user = objectMapper.convertValue(userPO, User)
        user.roleIds = userPO.roles*.id

        AuthenticationContext ctx = env.context
        ctx.userId = user.id
        ctx.setRoles(userPO.roles.collect { objectMapper.convertValue(it, Role) })

        return new LoginPayload(authToken: '12356789', user: user)
    }

    AuthenticationContext getAuthenticationContext(Optional<String> jwtToken) {
        return new AuthenticationContext(allowedOperations: DefaultPermissions.ALL_MISC_PERMISSIONS*.name)
    }

}
