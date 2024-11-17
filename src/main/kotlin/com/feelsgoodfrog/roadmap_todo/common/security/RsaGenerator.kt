package com.feelsgoodfrog.roadmap_todo.common.security

import com.feelsgoodfrog.roadmap_todo.domain.auth.dto.KeysResponseDto
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.util.*

@Component
class RsaGenerator {
    lateinit var keyPair: KeyPair

    @PostConstruct
    fun init() {
        val keyGenerator = KeyPairGenerator.getInstance(KEY_INSTANCE)
        keyGenerator.initialize(2048)
        keyPair = keyGenerator.genKeyPair()
    }

    fun publicKeyBody(): KeysResponseDto {
        val publicKey = Base64.getEncoder().encodeToString(keyPair.public.encoded)
        return KeysResponseDto(
            kty = KEY_INSTANCE,
            use = "sig",
            alg = "RS256",
            n = publicKey
        )
    }

    fun publicKey(): PublicKey {
        return keyPair.public
    }

    fun privateKey(): PrivateKey {
        return keyPair.private
    }

    companion object {
        private const val KEY_INSTANCE = "RSA"
    }
}
