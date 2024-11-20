package com.feelsgoodfrog.roadmap_todo.domain.user.service

import com.feelsgoodfrog.roadmap_todo.domain.user.dto.SignUpRequestDto
import com.feelsgoodfrog.roadmap_todo.domain.user.entity.UserRoles
import com.feelsgoodfrog.roadmap_todo.domain.user.entity.Users
import com.feelsgoodfrog.roadmap_todo.domain.user.repository.UsersRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UsersService(
    private val usersRepository: UsersRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun signUp(dto: SignUpRequestDto): Users {
        val users = Users(
            userName = dto.userName,
            email = dto.email,
            userPassword = passwordEncoder.encode(dto.password),
            roles = mutableListOf()
        )
        val userRole = UserRoles(
            userId = users,
            roles = "ROLE_USER"
        )
        users.roles.add(userRole)
        return usersRepository.save(users)
    }
}