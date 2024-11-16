package com.feelsgoodfrog.roadmap_todo.domain.auth.controller

import com.feelsgoodfrog.roadmap_todo.common.security.RsaGenerator
import com.feelsgoodfrog.roadmap_todo.domain.auth.dto.KeysResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class RsaController(
    private val rsaGenerator: RsaGenerator
) {

    @GetMapping("/keys")
    fun keys(): KeysResponseDto {
        return rsaGenerator.publicKey()
    }
}
