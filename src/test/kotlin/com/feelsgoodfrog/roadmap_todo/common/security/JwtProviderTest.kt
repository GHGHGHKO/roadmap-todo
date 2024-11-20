package com.feelsgoodfrog.roadmap_todo.common.security

import com.feelsgoodfrog.roadmap_todo.domain.user.entity.UserRoles
import com.feelsgoodfrog.roadmap_todo.domain.user.entity.Users
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class JwtProviderTest {

    @Autowired
    private lateinit var jwtProvider: JwtProvider

    private lateinit var users: Users

    @BeforeEach
    fun setup() {
        val localUsers = Users(
            name = "test",
            email = "test@test.com",
            userPassword = "test",
            roles = mutableListOf()
        )
        val userRole = UserRoles(
            userId = localUsers,
            roles = "ROLE_USER"
        )
        localUsers.roles.add(userRole)
        users = localUsers
    }

    @Test
    fun issueTokenTest() {
        // given
        val jwt = jwtProvider.issue(users)

        // when
        val verify = jwtProvider.verify(jwt)
        val validateToken = jwtProvider.validateToken(jwt)

        // then
        assertThat(verify)
        assertThat(validateToken)
    }
}
