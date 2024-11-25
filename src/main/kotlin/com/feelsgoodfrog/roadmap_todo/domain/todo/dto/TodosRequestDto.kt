package com.feelsgoodfrog.roadmap_todo.domain.todo.dto

import jakarta.validation.constraints.NotBlank

data class TodosRequestDto(
        @field:NotBlank
        val title: String,

        @field:NotBlank
        val description: String
)
