package com.feelsgoodfrog.roadmap_todo.domain.user.service

import com.feelsgoodfrog.roadmap_todo.domain.user.dto.SignUpRequestDto
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

    @Test
    fun signUp() {
        // given
        val signUp = SignUpRequestDto(
            name = "test",
            email = "test@test.com",
            password = "superawes0me~"
        )

        // when
        val result = usersService.signUp(signUp)

        // then
        assertThat(result.name).isEqualTo(signUp.name)
        assertThat(result.email).isEqualTo(signUp.email)
        assertThat(result.userPassword).isNotEqualTo(signUp.password)
    }
}
