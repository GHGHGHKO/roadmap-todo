package com.feelsgoodfrog.roadmap_todo.domain.user.repository

import com.feelsgoodfrog.roadmap_todo.domain.user.entity.UserRoles
import com.feelsgoodfrog.roadmap_todo.domain.user.entity.Users
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.transaction.annotation.Transactional
import kotlin.test.Test

@Transactional
@DataJpaTest
class UsersRepositoryTest {

    @Autowired
    private lateinit var usersRepository: UsersRepository

    @Test
    fun save() {
        // given
        val users = Users(
            userName = "test",
            email = "test@test.com",
            userPassword = "superawes0me~",
            roles = mutableListOf()
        )
        val userRole = UserRoles(
            userId = users,
            roles = "ROLE_USER"
        )
        users.roles.add(userRole)
        val expected = "test"

        // when
        val result = usersRepository.save(users)

        // then
        assertThat(result.userName).isEqualTo(expected)
    }
}
