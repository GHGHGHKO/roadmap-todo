package com.feelsgoodfrog.roadmap_todo.domain.auth.controller

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class RsaControllerTest(
    @Autowired private val mockMvc: MockMvc,
) {

    @Test
    fun keys() {
        mockMvc.get("/auth/keys")
            .andExpect {
                status { isOk() }
                jsonPath("$.kty") { isNotEmpty() }
                jsonPath("$.use") { isNotEmpty() }
                jsonPath("$.alg") { isNotEmpty() }
                jsonPath("$.n") { isNotEmpty() }
            }
    }
}