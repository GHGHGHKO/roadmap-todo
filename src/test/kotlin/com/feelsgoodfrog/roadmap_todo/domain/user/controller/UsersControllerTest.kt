package com.feelsgoodfrog.roadmap_todo.domain.user.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.feelsgoodfrog.roadmap_todo.common.security.JwtProvider
import com.feelsgoodfrog.roadmap_todo.domain.user.dto.LoginRequestDto
import com.feelsgoodfrog.roadmap_todo.domain.user.dto.RegisterRequestDto
import com.feelsgoodfrog.roadmap_todo.domain.user.entity.Users
import com.feelsgoodfrog.roadmap_todo.domain.user.service.UsersService
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
class UsersControllerTest(
    @Autowired private val usersService: UsersService,
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val jwtProvider: JwtProvider,
    @Autowired private val objectMapper: ObjectMapper
) {
    private val userEmail = UUID.randomUUID()
    private lateinit var token: String
    private lateinit var user: Users

    @BeforeEach
    fun setUp() {
        token = usersService.register(
            RegisterRequestDto(
                name = "test",
                email = "$userEmail@test.com",
                password = "superawes0me~"
            )
        ).token
        val userId = jwtProvider.userPrimaryKey(token)
        user = usersService.findById(userId)
    }

    @Test
    fun register() {
        val user = RegisterRequestDto(
            name = "test",
            email = "blahlah@test.com",
            password = "superawes0me~"
        )
        mockMvc.post("/register") {
            content = objectMapper.writeValueAsString(user)
            contentType = MediaType.APPLICATION_JSON
        }
            .andExpect {
                status { isOk() }
                jsonPath("$.token") { isNotEmpty() }
            }
    }

    @Test
    fun login() {
        val user = LoginRequestDto(
            email = user.email,
            password = "superawes0me~"
        )

        mockMvc.post("/login"){
            content = objectMapper.writeValueAsString(user)
            contentType = MediaType.APPLICATION_JSON
        }
            .andExpect {
                status { isOk() }
                jsonPath("$.token") { isNotEmpty() }
            }
    }
}
