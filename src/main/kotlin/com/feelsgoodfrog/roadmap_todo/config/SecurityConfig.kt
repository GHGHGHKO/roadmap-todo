package com.feelsgoodfrog.roadmap_todo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeHttpRequests {
                it.requestMatchers("/register", "/login").permitAll()
                    .anyRequest().hasRole("USER")
            }
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .build()
    }
}
