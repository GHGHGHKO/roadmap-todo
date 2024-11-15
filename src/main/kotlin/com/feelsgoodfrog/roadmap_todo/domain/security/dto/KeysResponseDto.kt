package com.feelsgoodfrog.roadmap_todo.domain.security.dto

data class KeysResponseDto(
    val kty: String,
    val use: String,
    val alg: String,
    val n: String,
)
