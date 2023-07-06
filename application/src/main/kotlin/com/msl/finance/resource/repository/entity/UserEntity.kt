package com.msl.finance.resource.repository.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(name = "user")
data class UserEntity(
    @Id
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val gender: String,
    val password: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?
)