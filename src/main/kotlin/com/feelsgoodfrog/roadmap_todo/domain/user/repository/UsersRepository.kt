package com.feelsgoodfrog.roadmap_todo.domain.user.repository

import com.feelsgoodfrog.roadmap_todo.domain.user.entity.Users
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UsersRepository: JpaRepository<Users, UUID> {
}
