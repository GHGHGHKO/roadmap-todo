package com.feelsgoodfrog.roadmap_todo.common.security

import com.feelsgoodfrog.roadmap_todo.domain.auth.dto.KeysResponseDto
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
import java.security.KeyPairGenerator
import java.util.*

@Component
class RsaGenerator {
    lateinit var secretKey: String

    @PostConstruct
    fun init() {
        val keyGenerator = KeyPairGenerator.getInstance(KEY_INSTANCE)
        keyGenerator.initialize(2048)
        val keyPair = keyGenerator.genKeyPair()
        secretKey = Base64.getEncoder().encodeToString(keyPair.public.encoded)
    }

    fun publicKey(): KeysResponseDto {
        return KeysResponseDto(
            kty = KEY_INSTANCE,
            use = "sig",
            alg = "RS256",
            n = secretKey
        )
    }

    companion object {
        private const val KEY_INSTANCE = "RSA"
    }
}
