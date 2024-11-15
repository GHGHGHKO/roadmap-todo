package com.feelsgoodfrog.roadmap_todo.common.entity

import com.feelsgoodfrog.roadmap_todo.config.entity.JpaAuditConfig
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(JpaAuditConfig::class)
class BaseEntity(
    @CreatedBy
    val createdBy: String = "",

    @CreatedDate
    val createdDate: LocalDateTime = LocalDateTime.now(),

    @LastModifiedBy
    val modifiedBy: String = "",

    @LastModifiedDate
    val modifiedDate: LocalDateTime = LocalDateTime.now(),
)
