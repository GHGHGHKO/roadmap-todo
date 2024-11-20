package com.feelsgoodfrog.roadmap_todo.common.security

import com.feelsgoodfrog.roadmap_todo.domain.user.entity.Users
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class JwtProviderTest {

    @Autowired
    private lateinit var jwtProvider: JwtProvider

    @Test
    fun issueTokenTest() {
        // given
        val users = Users(
            userName = "test",
            email = "test@test.com",
            password = "test",
        )
        val jwt = jwtProvider.issue(users)

        // when
        val verify = jwtProvider.verify(jwt)
        val validateToken = jwtProvider.validateToken(jwt)

        // then
        assertThat(verify)
        assertThat(validateToken)
    }
}
