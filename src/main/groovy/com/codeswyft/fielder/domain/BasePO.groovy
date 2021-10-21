package com.codeswyft.fielder.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.Version
import javax.validation.constraints.NotNull
import java.time.LocalDateTime

@CompileStatic
@MappedSuperclass
abstract class BasePO {

    @Id
    @Column(nullable = false, updatable = false)
    @NotNull
    private String id

    @Column(name = 'created_at', nullable = false, updatable = false)
    @NotNull
    private LocalDateTime createdAt

    @Column(name = 'created_by_id', nullable = false, updatable = false)
    @NotNull
    private String createdById

    @Column(name = 'updated_at', nullable = false)
    @NotNull
    LocalDateTime updatedAt

    @Column(name = 'updated_by_id', nullable = false)
    @NotNull
    String updatedById

    @Version
    @JsonIgnore
    Integer version

    BasePO() {
        LocalDateTime now = LocalDateTime.now()
        id = UUID.randomUUID().toString()
        createdAt = now
        updatedAt = now
    }

    void setId(String id) {
        if (id) {
            this.@id = id
        }
    }

    String getId() {
        return this.@id
    }

    void setCreatedById(String createdById) {
        this.@createdById = createdById
        if (!this.@updatedById) {
            this.@updatedById = createdById
        }
    }

    String getCreatedById() {
        return this.@createdById
    }

    void setCreatedAt(LocalDateTime createdAt) {
        this.@createdAt = createdAt
        if (!this.@updatedAt) {
            this.@updatedAt = createdAt
        }
    }

    LocalDateTime getCreatedAt() {
        return this.@createdAt
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof BasePO)) return false

        BasePO basePO = (BasePO) o

        if (id != basePO.id) return false
        if (version != basePO.version) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (version != null ? version.hashCode() : 0)
        return result
    }

}
