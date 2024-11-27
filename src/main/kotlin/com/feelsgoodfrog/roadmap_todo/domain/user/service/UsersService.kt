package com.feelsgoodfrog.roadmap_todo.domain.user.service

import com.feelsgoodfrog.roadmap_todo.common.exception.UserExistsException
import com.feelsgoodfrog.roadmap_todo.common.exception.UserNotFoundException
import com.feelsgoodfrog.roadmap_todo.common.security.JwtIssuedDto
import com.feelsgoodfrog.roadmap_todo.common.security.JwtProvider
import com.feelsgoodfrog.roadmap_todo.domain.user.dto.LoginRequestDto
import com.feelsgoodfrog.roadmap_todo.domain.user.dto.RegisterRequestDto
import com.feelsgoodfrog.roadmap_todo.domain.user.dto.LoginResponseDto
import com.feelsgoodfrog.roadmap_todo.domain.user.entity.UserRoles
import com.feelsgoodfrog.roadmap_todo.domain.user.entity.Users
import com.feelsgoodfrog.roadmap_todo.domain.user.entity.UsersJwt
import com.feelsgoodfrog.roadmap_todo.domain.user.repository.UsersJwtRepository
import com.feelsgoodfrog.roadmap_todo.domain.user.repository.UsersRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UsersService(
    private val usersRepository: UsersRepository,
    private val usersJwtRepository: UsersJwtRepository,
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
            user = users,
            roles = "ROLE_USER"
        )
        users.roles.add(userRole)

        val jwtDto = jwtProvider.issue(usersRepository.save(users))
        saveJwt(users, jwtDto)

        return LoginResponseDto(
            token = jwtDto.jwt
        )
    }

    fun login(dto: LoginRequestDto): LoginResponseDto {
        val user = usersRepository
            .findByEmail(dto.email)
            .filter { passwordEncoder.matches(dto.password, it.userPassword) }
            .orElseThrow { UserNotFoundException("User ${dto.email} not found") }

        val usersJwt = user.usersJwt
            ?: return LoginResponseDto(token = jwtProvider.issue(user).jwt)

        return if (usersJwt.expirationDate.isBefore(LocalDateTime.now())) {
            LoginResponseDto(token = jwtProvider.issue(user).jwt)
        } else {
            LoginResponseDto(token = usersJwt.jwt)
        }
    }

    fun saveJwt(users: Users, jwtDto: JwtIssuedDto) {
        val usersJwt = UsersJwt(
            user = users,
            jwt = jwtDto.jwt,
            expirationDate = jwtDto.expirationDate
        )
        usersJwtRepository.save(usersJwt)
    }

    fun existsUsersByEmail(email: String): Boolean {
        return usersRepository.existsUsersByEmail(email)
    }

    fun findById(id: String): Users {
        return usersRepository.findById(id)
                .orElseThrow { UserNotFoundException("User $id not found") }
    }
}
