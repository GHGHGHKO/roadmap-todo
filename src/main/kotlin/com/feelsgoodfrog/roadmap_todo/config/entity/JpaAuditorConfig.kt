package com.feelsgoodfrog.roadmap_todo.config.entity

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.auditing.DateTimeProvider
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.core.context.SecurityContextHolder
import java.time.LocalDateTime
import java.util.*

@Configuration
@EnableJpaAuditing(
    auditorAwareRef = "userContextProvider",
    dateTimeProviderRef = "dateTimeProvider"
)
class JpaAuditConfig {

    @Bean
    fun dateTimeProvider(): DateTimeProvider {
        return DateTimeProvider {
            Optional.of(LocalDateTime.now())
        }
    }

    @Bean
    fun userContextProvider(): AuditorAware<String> {
        return AuditorAware<String> {
            val authentication = SecurityContextHolder.getContext().authentication
            Optional.of(if (authentication != null) authentication.name else "Guest")
        }
    }
}
