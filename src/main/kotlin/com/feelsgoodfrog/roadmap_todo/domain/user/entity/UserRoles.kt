package com.feelsgoodfrog.roadmap_todo.domain.user.entity

import com.feelsgoodfrog.roadmap_todo.common.entity.BaseEntity
import jakarta.persistence.*

@SequenceGenerator(
    name = "UserRolesSeqGenerator",
    sequenceName = "UserRolesSeq",
    allocationSize = 1
)
@Entity
class UserRoles(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserRolesSeqGenerator")
    val id: Long? = null,

    @ManyToOne(cascade = [(CascadeType.MERGE)])
    val userId: Users,

    val roles: String
) : BaseEntity()
