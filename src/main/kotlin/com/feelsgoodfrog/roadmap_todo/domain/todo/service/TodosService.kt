package com.feelsgoodfrog.roadmap_todo.domain.todo.service

import com.feelsgoodfrog.roadmap_todo.domain.todo.dto.TodosRequestDto
import com.feelsgoodfrog.roadmap_todo.domain.todo.dto.TodosResponseDto
import com.feelsgoodfrog.roadmap_todo.domain.todo.entity.UsersTodos
import com.feelsgoodfrog.roadmap_todo.domain.todo.repository.UsersTodosRepository
import org.springframework.stereotype.Service

@Service
class TodosService(
        private val todosRepository: UsersTodosRepository
) {

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
}
