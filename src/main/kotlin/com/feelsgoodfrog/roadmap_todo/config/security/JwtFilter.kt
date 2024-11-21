package com.feelsgoodfrog.roadmap_todo.config.security

import com.feelsgoodfrog.roadmap_todo.common.security.JwtProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean

class JwtFilter(
    private val jwtProvider: JwtProvider
): GenericFilterBean() {
    override fun doFilter(p0: ServletRequest?, p1: ServletResponse?, p2: FilterChain?) {
        val jwt: String? = jwtProvider.resolveToken(p0 as HttpServletRequest)

        if (jwt != null && jwtProvider.validateToken(jwt)) {
            val authentication = jwtProvider.userAuthentication(jwt)
            SecurityContextHolder.getContext().authentication = authentication
        }

        p2?.doFilter(p0, p1)
    }

}
