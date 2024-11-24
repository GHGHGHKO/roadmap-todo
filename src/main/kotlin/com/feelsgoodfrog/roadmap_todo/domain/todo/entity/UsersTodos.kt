package com.feelsgoodfrog.roadmap_todo.domain.todo.entity

import com.feelsgoodfrog.roadmap_todo.common.entity.BaseEntity
import jakarta.persistence.*

@SequenceGenerator(
    name = "UsersTodosSeqGenerator",
    sequenceName = "UsersTodosSeq",
    allocationSize = 1
)
@Entity
class UsersTodos(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UsersTodosSeqGenerator")
    val id: Long? = null,

    val title: String,

    val description: String
): BaseEntity()
