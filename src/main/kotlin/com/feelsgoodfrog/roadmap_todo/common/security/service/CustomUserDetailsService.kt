package com.feelsgoodfrog.roadmap_todo.common.security.service

import com.feelsgoodfrog.roadmap_todo.domain.user.repository.UsersRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val usersRepository: UsersRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return usersRepository.findById(username)
            .orElseThrow { UsernameNotFoundException("$username not found") }
    }
}
