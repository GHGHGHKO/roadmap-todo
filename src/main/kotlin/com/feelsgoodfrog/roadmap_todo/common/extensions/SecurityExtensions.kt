package com.feelsgoodfrog.roadmap_todo.common.extensions

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails

fun Authentication?.getUsername(): String {
    return if (this?.principal is UserDetails) {
        (this.principal as UserDetails).username
    } else {
        throw IllegalAccessException("Principal is not UserDetails")
    }
}

fun getCurrentUsername(): String {
    return SecurityContextHolder.getContext().authentication.getUsername()
}
