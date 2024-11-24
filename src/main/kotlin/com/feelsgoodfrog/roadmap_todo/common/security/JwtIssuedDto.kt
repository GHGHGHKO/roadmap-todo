package com.feelsgoodfrog.roadmap_todo.common.security

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

data class JwtIssuedDto(
    val jwt: String,

    val expirationDate: LocalDateTime
) {
    constructor(
        jwt: String,
        expirationDate: Date
    ) : this(
        jwt,
        expirationDate.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
    )
}
