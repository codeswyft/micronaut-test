package com.codeswyft.fielder.graphql.validation

import com.codeswyft.fielder.modules.Violation
import com.fasterxml.jackson.annotation.JsonIgnore
import graphql.ErrorType
import graphql.GraphQLError
import graphql.language.SourceLocation
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

@Slf4j
@CompileStatic
class ValidationError extends RuntimeException implements GraphQLError {

    private final List<String> violations

    ValidationError(Violation... violations) {
        this.violations = violations?.collect { it.violationKey } as List<String>
    }

    ValidationError(List<String> violations) {
        this.violations = violations
    }

    List<String> getViolations() {
        return violations
    }

    @Override
    String getMessage() {
        return 'Validation errors'
    }

    @Override
    List<SourceLocation> getLocations() {
        return null
    }

    @Override
    ErrorType getErrorType() {
        return ErrorType.ValidationError
    }

    @JsonIgnore
    // To avoid showing the stacktrace to the GraphQL clients.
    @Override
    StackTraceElement[] getStackTrace() {
        return super.getStackTrace()
    }

    /**
     * @return a map of error extensions or null if there are none
     */
    Map<String, Object> getExtensions() {
        Map<String, Object> validationErrors = new LinkedHashMap<>()
        validationErrors['code'] = violations
        return validationErrors
    }

}
