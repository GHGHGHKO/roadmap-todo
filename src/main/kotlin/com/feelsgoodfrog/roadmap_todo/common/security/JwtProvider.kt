package com.feelsgoodfrog.roadmap_todo.common.security

import com.feelsgoodfrog.roadmap_todo.domain.user.entity.Users
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtProvider(
    private val rsaGenerator: RsaGenerator,
    private val userDetailService: UserDetailsService
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
        return parser().isSigned(jwt)
    }

    fun resolveToken(request: HttpServletRequest): String? {
        return request.getHeader(HttpHeaders.AUTHORIZATION)
    }

    fun validateToken(jwt: String): Boolean {
        return try {
            val isVerify = verify(jwt)
            val isJwtExpired = parser()
                .parseSignedClaims(jwt)
                .payload
                .expiration
                .after(Date())

            isVerify && isJwtExpired
        } catch (e: Exception) {
            false
        }
    }

    fun userAuthentication(jwt: String): Authentication {
        val primaryKey = userPrimaryKey(jwt)
        val userDetails = userDetailService.loadUserByUsername(primaryKey)
        return UsernamePasswordAuthenticationToken(userDetails, userDetails.password, userDetails.authorities)
    }

    private fun userPrimaryKey(jwt: String): String {
        return parser()
            .parseSignedClaims(jwt)
            .payload
            .subject
    }

    private fun parser(): JwtParser {
        val publicKey = rsaGenerator.publicKey()
        return Jwts.parser()
            .verifyWith(publicKey)
            .build()
    }

    companion object {
        private const val ISSUER = "roadmap-todo"
        private const val JWT_EXPIRATION = 60 * 60 * 24 * 1000
    }
}
