package com.msl.finance.resource.repository.entity

import com.msl.finance.model.User
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(name = "users")
data class UserEntity(
    @Id
    val id: String?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val gender: String,
    val password: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?
) {

    constructor(user: User): this(
        id = null,
        firstName = user.firstName,
        lastName = user.lastName,
        email = user.email,
        gender = user.gender,
        password = user.password,
        createdAt = LocalDateTime.now(),
        updatedAt = user.createdAt
    )

    fun toDomain() = User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        gender = gender,
        password = password,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}