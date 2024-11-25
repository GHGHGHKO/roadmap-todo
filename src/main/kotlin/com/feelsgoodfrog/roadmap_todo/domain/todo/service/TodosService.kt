package com.feelsgoodfrog.roadmap_todo.domain.todo.service

import com.feelsgoodfrog.roadmap_todo.domain.todo.dto.TodosRequestDto
import com.feelsgoodfrog.roadmap_todo.domain.todo.dto.TodosResponseDto
import com.feelsgoodfrog.roadmap_todo.domain.todo.entity.UsersTodos
import com.feelsgoodfrog.roadmap_todo.domain.todo.repository.UsersTodosRepository
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class TodosService(
        private val todosRepository: UsersTodosRepository
) {
    fun findById(id: Long): UsersTodos {
        return todosRepository.findById(id)
                .orElseThrow { IllegalArgumentException("Not found $id id") }
    }

    fun save(usersTodos: UsersTodos): UsersTodos {
        return todosRepository.save(usersTodos)
    }

    fun save(dto: TodosRequestDto): TodosResponseDto {
        val todo = todosRepository.save(
                UsersTodos(
                        title = dto.title,
                        description = dto.description
                )
        )

        return TodosResponseDto(
                id = todo.id,
                title = todo.title,
                description = todo.description
        )
    }

    fun update(id: Long, dto: TodosRequestDto): TodosResponseDto {
        val todo = findById(id)
        todo.update(dto)
        save(todo)
        return TodosResponseDto(
                id = todo.id,
                title = todo.title,
                description = todo.description
        )
    }
}
