package com.codeswyft.fielder.repositories

import com.codeswyft.fielder.domain.BasePO
import groovy.transform.CompileStatic

import javax.validation.constraints.NotBlank

@CompileStatic
interface BaseRepository<T extends BasePO> {

    Optional<T> findById(@NotBlank String id)

    List<T> findByIds(Collection<String> ids)

    T update(T entity)

    T create(T entity)

    T deleteById(@NotBlank String id)

    @Deprecated(since = "only use for tests or special seeding")
    T createBackdoor(T entity)
}
