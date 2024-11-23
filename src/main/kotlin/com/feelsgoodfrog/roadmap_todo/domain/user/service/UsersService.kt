package com.feelsgoodfrog.roadmap_todo.domain.user.service

import com.feelsgoodfrog.roadmap_todo.common.exception.UserExistsException
import com.feelsgoodfrog.roadmap_todo.common.exception.UserNotFoundException
import com.feelsgoodfrog.roadmap_todo.common.security.JwtProvider
import com.feelsgoodfrog.roadmap_todo.domain.user.dto.LoginRequestDto
import com.feelsgoodfrog.roadmap_todo.domain.user.dto.RegisterRequestDto
import com.feelsgoodfrog.roadmap_todo.domain.user.dto.LoginResponseDto
import com.feelsgoodfrog.roadmap_todo.domain.user.entity.UserRoles
import com.feelsgoodfrog.roadmap_todo.domain.user.entity.Users
import com.feelsgoodfrog.roadmap_todo.domain.user.repository.UsersRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UsersService(
    private val usersRepository: UsersRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider
) {
    fun register(dto: RegisterRequestDto): LoginResponseDto {
        if (existsUsersByEmail(dto.email)) {
            throw UserExistsException("Already exists ${dto.email} email")
        }
        val users = Users(
            name = dto.name,
            email = dto.email,
            userPassword = passwordEncoder.encode(dto.password),
            roles = mutableListOf()
        )
        val userRole = UserRoles(
            userId = users,
            roles = "ROLE_USER"
        )
        users.roles.add(userRole)

        return LoginResponseDto(
            token = jwtProvider.issue(usersRepository.save(users))
        )
    }

    fun login(dto: LoginRequestDto): LoginResponseDto {
        val user = usersRepository
            .findByEmail(dto.email)
            .filter { u -> passwordEncoder.matches(dto.password, u.userPassword) }
            .orElseThrow{ UserNotFoundException("User ${dto.email} not found") }

        return LoginResponseDto(
            token = jwtProvider.issue(user)
        )
    }

    fun existsUsersByEmail(email: String): Boolean {
        return usersRepository.existsUsersByEmail(email)
    }
}
