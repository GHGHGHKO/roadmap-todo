package com.feelsgoodfrog.roadmap_todo.domain.user.service

import com.feelsgoodfrog.roadmap_todo.domain.user.dto.SignUpRequestDto
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
        return usersRepository.save(
            Users(
                userName = dto.userName,
                email = dto.email,
                password = passwordEncoder.encode(dto.password),
            )
        )
    }
}
