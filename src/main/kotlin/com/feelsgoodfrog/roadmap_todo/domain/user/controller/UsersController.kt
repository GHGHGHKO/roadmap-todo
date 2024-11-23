package com.feelsgoodfrog.roadmap_todo.domain.user.controller

import com.feelsgoodfrog.roadmap_todo.domain.user.dto.LoginRequestDto
import com.feelsgoodfrog.roadmap_todo.domain.user.dto.RegisterRequestDto
import com.feelsgoodfrog.roadmap_todo.domain.user.dto.LoginResponseDto
import com.feelsgoodfrog.roadmap_todo.domain.user.service.UsersService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController(
    private val usersService: UsersService
) {

    @PostMapping("/register")
    fun register(
        @RequestBody dto: RegisterRequestDto
    ): ResponseEntity<LoginResponseDto> {
        return ResponseEntity.ok()
            .body(usersService.register(dto))
    }

    @PostMapping("/login")
    fun login(
        @RequestBody dto: LoginRequestDto
    ): ResponseEntity<LoginResponseDto> {
        return ResponseEntity.ok(usersService.login(dto))
    }
}
