package com.feelsgoodfrog.roadmap_todo.domain.user.entity

import com.feelsgoodfrog.roadmap_todo.common.entity.BaseEntity
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Entity
class Users(
    @Id
    val id: String = UUID.randomUUID().toString(),

    val name: String,

    val email: String,

    val userPassword: String,

    @OneToMany(mappedBy = "userId", cascade = [CascadeType.ALL])
    val roles: MutableList<UserRoles>
): BaseEntity(), UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles
            .map { roles -> SimpleGrantedAuthority(roles.roles) }
            .toMutableList()
    }

    override fun getPassword(): String {
        return userPassword
    }

    override fun getUsername(): String {
        return email
    }

}
