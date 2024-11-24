package com.feelsgoodfrog.roadmap_todo.domain.user.repository

import com.feelsgoodfrog.roadmap_todo.domain.user.entity.UsersJwt
import org.springframework.data.jpa.repository.JpaRepository

interface UsersJwtRepository: JpaRepository<UsersJwt, Long>
