package com.feelsgoodfrog.roadmap_todo.common.security

import com.feelsgoodfrog.roadmap_todo.domain.security.dto.KeysResponseDto
import org.springframework.stereotype.Component
import java.security.KeyPairGenerator
import java.util.*

@Component
class RsaGenerator {

    fun publicKey(): KeysResponseDto {
        val keyGenerator = KeyPairGenerator.getInstance(KEY_INSTANCE)
        keyGenerator.initialize(2048)
        val keyPair = keyGenerator.genKeyPair()
        return KeysResponseDto(
            kty = KEY_INSTANCE,
            use = "sig",
            alg = "RS256",
            n = Base64.getEncoder().encodeToString(keyPair.public.encoded)
        )
    }

    companion object {
        private const val KEY_INSTANCE = "RSA"
    }
}
