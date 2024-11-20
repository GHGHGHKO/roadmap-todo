package com.feelsgoodfrog.roadmap_todo.domain.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SignUpRequestDto(
    @field:Email
    @field:NotBlank
    val email: String,

    @field:Size(min = 1, max = 30, message = "username must be between 1 and 30")
    val name: String,

    @field:NotBlank
    @field:Size(min = 1, max = 30, message = "password must be between 1 and 30")
    val password: String,
)
