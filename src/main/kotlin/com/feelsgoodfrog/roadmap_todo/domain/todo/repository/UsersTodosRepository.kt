package com.feelsgoodfrog.roadmap_todo.domain.todo.repository

import com.feelsgoodfrog.roadmap_todo.domain.todo.entity.UsersTodos
import com.feelsgoodfrog.roadmap_todo.domain.user.entity.Users
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface UsersTodosRepository : JpaRepository<UsersTodos, Long> {
    fun findByUser(users: Users, pageable: Pageable): Page<UsersTodos>
}
