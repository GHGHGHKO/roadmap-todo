package com.feelsgoodfrog.roadmap_todo.domain.todo.repository

import com.feelsgoodfrog.roadmap_todo.domain.todo.entity.UsersTodos
import org.springframework.data.jpa.repository.JpaRepository

interface UsersTodosRepository: JpaRepository<UsersTodos, Long>
