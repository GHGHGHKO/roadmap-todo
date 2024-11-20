package com.feelsgoodfrog.roadmap_todo.domain.user.repository

import com.feelsgoodfrog.roadmap_todo.domain.user.entity.UserRoles
import com.feelsgoodfrog.roadmap_todo.domain.user.entity.Users
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.transaction.annotation.Transactional
import kotlin.test.Test

@Transactional
@DataJpaTest
class UsersRepositoryTest {

    @Autowired
    private lateinit var usersRepository: UsersRepository
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
    fun save() {
        // given
        val expected = "test"

        // when
        val result = usersRepository.save(users)

        // then
        assertThat(result.name).isEqualTo(expected)
    }
}
