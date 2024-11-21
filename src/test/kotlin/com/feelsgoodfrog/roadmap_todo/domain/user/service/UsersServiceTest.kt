package com.feelsgoodfrog.roadmap_todo.domain.user.service

import com.feelsgoodfrog.roadmap_todo.common.security.JwtProvider
import com.feelsgoodfrog.roadmap_todo.domain.user.dto.RegisterRequestDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class UsersServiceTest {

    @Autowired
    private lateinit var usersService: UsersService

    @Autowired
    private lateinit var jwtProvider: JwtProvider

    @Test
    fun register() {
        // given
        val signUp = RegisterRequestDto(
            name = "test",
            email = "test@test.com",
            password = "superawes0me~"
        )

        // when
        val result = usersService.register(signUp)
        val validateToken = jwtProvider.validateToken(result.token)

        // then
        assertThat(validateToken)
    }
}
