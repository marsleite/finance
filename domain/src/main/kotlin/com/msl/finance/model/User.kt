package com.msl.finance.model

import java.time.LocalDateTime

data class User(
    val id: String?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val gender: String,
    val password: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?
)