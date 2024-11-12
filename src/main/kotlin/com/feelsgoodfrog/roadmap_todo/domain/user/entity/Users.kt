package com.feelsgoodfrog.roadmap_todo.domain.user.entity

import com.feelsgoodfrog.roadmap_todo.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
class Users(
    @Id
    val id: String = UUID.randomUUID().toString(),

    val userName: String,

    val email: String,

    val password: String,
): BaseEntity()
