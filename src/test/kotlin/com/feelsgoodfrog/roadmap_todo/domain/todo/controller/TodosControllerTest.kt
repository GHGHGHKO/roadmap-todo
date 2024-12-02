package com.feelsgoodfrog.roadmap_todo.domain.todo.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.feelsgoodfrog.roadmap_todo.common.security.JwtProvider
import com.feelsgoodfrog.roadmap_todo.domain.todo.dto.TodosRequestDto
import com.feelsgoodfrog.roadmap_todo.domain.todo.service.TodosService
import com.feelsgoodfrog.roadmap_todo.domain.user.dto.RegisterRequestDto
import com.feelsgoodfrog.roadmap_todo.domain.user.entity.Users
import com.feelsgoodfrog.roadmap_todo.domain.user.service.UsersService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*
import java.util.UUID

@SpringBootTest
@AutoConfigureMockMvc
class TodosControllerTest(
    @Autowired private val usersService: UsersService,
    @Autowired private val todosService: TodosService,
    @Autowired private val jwtProvider: JwtProvider,
    @Autowired private val mockMvc: MockMvc,
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
        todosSave()
    }

    @Test
    fun get() {
        mockMvc.get("/todos") {
            header(HttpHeaders.AUTHORIZATION, token)
        }
            .andExpect {
                status { isOk() }
                jsonPath("$.content.length()") { value(20) }
                jsonPath("$.totalElements") { value(31) }
            }
    }

    @Test
    fun getWithPaging() {
        val response = mockMvc.get("/todos?page=1&size=10&sort=id,desc") {
            header(HttpHeaders.AUTHORIZATION, token)
        }
            .andExpect {
                status { isOk() }
                jsonPath("$.content.length()") { value(10) }
            }
            .andReturn()
            .response
            .contentAsString

        val json = ObjectMapper().readTree(response)
        val firstId = json["content"][0]["id"].asInt()
        val lastId = json["content"][9]["id"].asInt()

        assertThat(firstId > lastId)
    }

    @Test
    fun save() {
        val todo = TodosRequestDto(
            title = "save-todo",
            description = "save-todo-description"
        )
        mockMvc.post("/todos") {
            header(HttpHeaders.AUTHORIZATION, token)
            content = objectMapper.writeValueAsString(todo)
            contentType = MediaType.APPLICATION_JSON
        }
            .andExpect {
                status { isOk() }
                jsonPath("$.id") { isNotEmpty() }
                jsonPath("$.title") { value("save-todo") }
                jsonPath("$.description") { value("save-todo-description") }
            }
    }

    @Test
    fun update() {
        val todo = TodosRequestDto(
            title = "save-todo",
            description = "save-todo-description"
        )

        mockMvc.put("/todos/43") {
            header(HttpHeaders.AUTHORIZATION, token)
            content = objectMapper.writeValueAsString(todo)
            contentType = MediaType.APPLICATION_JSON
        }
            .andExpect {
                status { isOk() }
                jsonPath("$.id") { isNotEmpty() }
                jsonPath("$.title") { value("save-todo") }
                jsonPath("$.description") { value("save-todo-description") }
            }
    }

    @Test
    fun delete() {
        mockMvc.delete("/todos/3") {
            header(HttpHeaders.AUTHORIZATION, token)
            contentType = MediaType.APPLICATION_JSON
        }
            .andExpect {
                status { is2xxSuccessful() }
            }
    }

    private fun todosSave() {
        for (i in 0..30) {
            todosService.save(
                TodosRequestDto(
                    title = "{$i}this-is-title",
                    description = "this-is-description"
                ),
                user.id
            )
        }
    }
}
