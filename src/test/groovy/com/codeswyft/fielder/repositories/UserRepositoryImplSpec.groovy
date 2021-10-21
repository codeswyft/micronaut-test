package com.codeswyft.fielder.repositories

import com.codeswyft.fielder.domain.UserPO
import com.codeswyft.fielder.graphql.BaseFunctionalSpec
import com.codeswyft.fielder.model.User
import com.codeswyft.fielder.model.UserStatus
import com.codeswyft.fielder.util.dataseed.DefaultRoles
import spock.lang.Stepwise

@Stepwise
class UserRepositoryImplSpec extends BaseFunctionalSpec {

    def 'check repo wired'() {
        expect:
        userRepository
    }

    def 'get user by email (not present)'() {
        when:
        Optional<UserPO> result = userRepository.findByEmail('test@test.com')

        then:
        !result.isPresent()
    }

    def 'create user (persist)'() {
        given:
        User user = new User()
        user.firstName = 'Joe'
        user.lastName = 'Blow'
        user.status = UserStatus.ACTIVE
        user.email = 'test@test.com'
        user.roleIds = [DefaultRoles.FIELD_TECH.id]
        user.createdById = 'ben'
        user.updatedById = 'ben'
        user.organizationId = '1'

        when:
        UserPO result = userRepository.create(user, 'ilovemyjob1')

        then:
        result
        result.createdById == 'ben'
        result.updatedById == 'ben'
        result.id
        result.createdAt
        result.updatedAt
        result.email == 'test@test.com'
        result.firstName == 'Joe'
        result.lastName == 'Blow'
        result.organizationId == '1'
    }

    def 'get user by email (present)'() {
        when:
        Optional<UserPO> result = userRepository.findByEmail('test@test.com')

        then:
        result.isPresent()
    }
}
