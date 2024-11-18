package com.feelsgoodfrog.roadmap_todo.common.security

import com.feelsgoodfrog.roadmap_todo.domain.user.entity.Users
import io.jsonwebtoken.Jwts
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtProvider(
    private val rsaGenerator: RsaGenerator
) {

    fun issue(users: Users): String {
        val now = Date()
        val claims = Jwts.claims()
            .subject(users.id)
            .build()
        val expirationDate = Date(now.time + JWT_EXPIRATION)
        val privateKey = rsaGenerator.privateKey()

        return Jwts.builder()
            .claims(claims)
            .issuer(ISSUER)
            .issuedAt(now)
            .expiration(expirationDate)
            .signWith(privateKey, Jwts.SIG.RS256)
            .compact()
    }

    fun verify(jwt: String): Boolean {
        val publicKey = rsaGenerator.publicKey()
        return Jwts.parser()
            .verifyWith(publicKey)
            .build()
            .isSigned(jwt)
    }

    fun resolveToken(request: HttpServletRequest): String? {
        return request.getHeader(HttpHeaders.AUTHORIZATION)
    }

    companion object {
        private const val ISSUER = "roadmap-todo"
        private const val JWT_EXPIRATION = 60 * 60 * 24 * 1000
    }
}
