package com.feelsgoodfrog.roadmap_todo.domain.todo.controller

import com.feelsgoodfrog.roadmap_todo.common.extensions.getCurrentUsername
import com.feelsgoodfrog.roadmap_todo.domain.todo.dto.TodosRequestDto
import com.feelsgoodfrog.roadmap_todo.domain.todo.dto.TodosResponseDto
import com.feelsgoodfrog.roadmap_todo.domain.todo.service.TodosService
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/todos")
class TodosController(
        private val todosService: TodosService
) {

    @GetMapping
    @RateLimiter(name = "todos-get")
    fun get(
        pageable: Pageable
    ): ResponseEntity<Page<TodosResponseDto>> {
        val userId = getCurrentUsername()
        return ResponseEntity.ok()
            .body(todosService.get(userId, pageable))
    }

    @PostMapping
    fun save(
            @Valid @RequestBody dto: TodosRequestDto
    ): ResponseEntity<TodosResponseDto> {
        val userId = getCurrentUsername()
        return ResponseEntity.ok()
                .body(todosService.save(dto, userId))
    }

    @PutMapping("/{id}")
    fun update(
            @PathVariable id: Long,
            @Valid @RequestBody dto: TodosRequestDto
    ): ResponseEntity<TodosResponseDto> {
        val userId = getCurrentUsername()
        return ResponseEntity.ok()
                .body(todosService.update(id, dto, userId))
    }

    @DeleteMapping("/{id}")
    fun delete(
            @PathVariable id: Long
    ): ResponseEntity<Unit> {
        val userId = getCurrentUsername()
        todosService.delete(id, userId)
        return ResponseEntity.noContent().build()
    }
}
