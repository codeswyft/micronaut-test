package com.codeswyft.fielder.util

import com.codeswyft.fielder.domain.UserPO
import com.codeswyft.fielder.graphql.validation.ValidationError
import com.codeswyft.fielder.model.AuthenticationContext
import com.codeswyft.fielder.modules.Violation
import com.codeswyft.fielder.repositories.UserRepository
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

@Slf4j
@CompileStatic
class ValidationUtil {

    static void throwValidationError(String msg, Violation... violations) {
        // error message is logged in the create method
        throw createValidationError(msg, violations)
    }

    static ValidationError createValidationError(String msg, Violation... violations) {
        log.warn(msg)
        return new ValidationError(violations.collect { it.violationKey })
    }

    static UserPO validateUser(String userId, UserRepository userRepository) {
        return userRepository.findById(userId).orElseThrow {
            createValidationError("Not Authorized - invalid user id: ${userId}", Violation.INVALID_USER)
        }
    }

    static UserPO validateUser(AuthenticationContext ctx, UserRepository userRepository) {
        return userRepository.findById(ctx.userId).orElseThrow {
            createValidationError("Not Authorized - invalid user id: ${ctx.userId}", Violation.INVALID_USER)
        }
    }

    static final boolean validateEmail(final String email) {
        return email ==~ /[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[A-Za-z]{2,4}/
    }

}
