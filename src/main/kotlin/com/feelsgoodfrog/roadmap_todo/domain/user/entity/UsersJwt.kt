package com.feelsgoodfrog.roadmap_todo.domain.user.entity

import com.feelsgoodfrog.roadmap_todo.common.entity.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@SequenceGenerator(
    name = "UsersJwtSeqGenerator",
    sequenceName = "UsersJwtSeq",
    allocationSize = 1
)
@Entity
class UsersJwt(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UsersJwtSeqGenerator")
    val id: Long? = null,

    @OneToOne
    val userId: Users,

    @Column(length = 1000)
    val jwt: String,

    val expiredDate: LocalDateTime
): BaseEntity()
