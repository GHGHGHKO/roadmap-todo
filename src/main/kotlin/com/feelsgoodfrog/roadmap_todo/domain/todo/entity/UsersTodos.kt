package com.feelsgoodfrog.roadmap_todo.domain.todo.entity

import com.feelsgoodfrog.roadmap_todo.common.entity.BaseEntity
import com.feelsgoodfrog.roadmap_todo.domain.todo.dto.TodosRequestDto
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
        val id: Long = 0,

        var title: String,

        var description: String
) : BaseEntity() {
    fun update(dto: TodosRequestDto) {
        this.title = dto.title
        this.description = dto.description
    }
}
