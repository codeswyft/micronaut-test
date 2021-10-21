package com.codeswyft.fielder.domain

import com.codeswyft.fielder.model.UserStatus
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table
import javax.validation.constraints.NotNull
import java.time.LocalDateTime

@Entity
@CompileStatic
@Table(name = 'users')
// note: table name plural to avoid using psql keyword
@EqualsAndHashCode(callSuper = true, includes = ['email', 'organizationId'])
class UserPO extends BasePO {

    @Column(nullable = true, name = 'first_name')
    String firstName

    @Column(nullable = true, name = 'last_name')
    String lastName

    @Column(nullable = false, updatable = true)
    @NotNull
    String password

    @Column(nullable = false, updatable = true)
    @Enumerated(EnumType.STRING)
    @NotNull
    UserStatus status

    @Column(nullable = true, name = 'last_login')
    LocalDateTime lastLogin

    @Column(nullable = false, unique = true)
    String email // this also serves as the username

    @Column(nullable = true, name = 'job_title')
    String jobTitle

    @Column(nullable = true, name = 'mobile_phone_number')
    String mobilePhoneNumber

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    Set<RolePO> roles = new HashSet<RolePO>()

    @Column(nullable = false, name = 'organization_id')
    String organizationId

    @Column(nullable = true, name = 'time_zone')
    String timeZone

    @JsonIgnore
    String getPassword() {
        return password
    }

    @JsonProperty
    void setPassword(String password) {
        this.password = password
    }

}
