package com.feelsgoodfrog.roadmap_todo.config.security

import com.feelsgoodfrog.roadmap_todo.common.security.JwtProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
    private val jwtProvider: JwtProvider,
    private val customAuthenticationEntryPoint: CustomAuthenticationEntryPoint
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeHttpRequests {
                it.requestMatchers("/register", "/login", "/auth/keys").permitAll()
                    .anyRequest().hasRole("USER")
            }
            .addFilterBefore(
                JwtFilter(jwtProvider),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .exceptionHandling { it.authenticationEntryPoint(customAuthenticationEntryPoint) }
            .build()
    }
}
