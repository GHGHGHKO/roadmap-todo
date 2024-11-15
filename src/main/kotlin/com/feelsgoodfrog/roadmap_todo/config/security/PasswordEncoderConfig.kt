package com.feelsgoodfrog.roadmap_todo.config.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class PasswordEncoderConfig {

    @Bean
    fun passwordEncoder(@Value("\${passwordEncoder.strength:13}") strength: Int): PasswordEncoder =
        BCryptPasswordEncoder(strength)
}
