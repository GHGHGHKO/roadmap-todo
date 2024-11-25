package com.feelsgoodfrog.roadmap_todo.domain.todo.controller

import com.feelsgoodfrog.roadmap_todo.domain.todo.dto.TodosRequestDto
import com.feelsgoodfrog.roadmap_todo.domain.todo.dto.TodosResponseDto
import com.feelsgoodfrog.roadmap_todo.domain.todo.service.TodosService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/todos")
class TodosController(
        private val todosService: TodosService
) {

    @PostMapping
    fun save(
            @Valid @RequestBody dto: TodosRequestDto
    ): ResponseEntity<TodosResponseDto> {
        return ResponseEntity.ok()
                .body(todosService.save(dto))
    }
}
