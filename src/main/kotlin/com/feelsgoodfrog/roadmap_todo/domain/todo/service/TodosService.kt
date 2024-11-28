package com.feelsgoodfrog.roadmap_todo.domain.todo.service

import com.feelsgoodfrog.roadmap_todo.domain.todo.dto.TodosRequestDto
import com.feelsgoodfrog.roadmap_todo.domain.todo.dto.TodosResponseDto
import com.feelsgoodfrog.roadmap_todo.domain.todo.entity.UsersTodos
import com.feelsgoodfrog.roadmap_todo.domain.todo.repository.UsersTodosRepository
import com.feelsgoodfrog.roadmap_todo.domain.user.service.UsersService
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class TodosService(
        private val todosRepository: UsersTodosRepository,
        private val usersService: UsersService
) {
    fun findById(id: Long): UsersTodos {
        return todosRepository.findById(id)
                .orElseThrow { IllegalArgumentException("Not found $id id") }
    }

    fun save(usersTodos: UsersTodos): UsersTodos {
        return todosRepository.save(usersTodos)
    }

    fun save(dto: TodosRequestDto, userId: String): TodosResponseDto {
        val user = usersService.findById(userId)
        val todo = save(
                UsersTodos(
                        title = dto.title,
                        description = dto.description,
                        user = user
                )
        )

        return TodosResponseDto(
                id = todo.id,
                title = todo.title,
                description = todo.description
        )
    }

    fun update(id: Long, dto: TodosRequestDto, userId: String): TodosResponseDto {
        val todo = findById(id)
        isValidUser(todo, userId)
        todo.update(dto)
        save(todo)
        return TodosResponseDto(
                id = todo.id,
                title = todo.title,
                description = todo.description
        )
    }

    fun delete(id: Long, userId: String) {
        val todo = findById(id)
        isValidUser(todo, userId)
        todosRepository.deleteById(id)
    }

    private fun isValidUser(todo: UsersTodos, userId: String) {
        if (todo.user.id != userId) {
            throw IllegalAccessException("Forbidden") // todo 403 ERROR
        }
    }
}
