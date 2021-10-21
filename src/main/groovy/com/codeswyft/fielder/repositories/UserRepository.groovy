package com.codeswyft.fielder.repositories

import com.codeswyft.fielder.domain.UserPO
import com.codeswyft.fielder.model.User
import groovy.transform.CompileStatic

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@CompileStatic
interface UserRepository extends BaseRepository<UserPO> {

    UserPO create(@NotNull User user, @NotBlank String password)

    Optional<UserPO> findByEmail(@NotBlank String email)

    UserPO update(@NotNull User user)

    void updateLastLogin(@NotBlank String userId)

    void updatePassword(@NotNull UserPO userPO, @NotBlank String password)

    UserPO lockForPasswordChange(@NotBlank String userId)


}
