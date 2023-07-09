package com.msl.finance.builder

import com.msl.finance.model.User
import java.time.LocalDateTime

class UserBuilder(
  var id: Long? = 29,
  var firstName: String = "Marcelo",
  var lastName: String = "Leite",
  var email: String = "test@email.com",
  var gender: String = "Male",
  var password: String = "1234",
  var createdAt: LocalDateTime = LocalDateTime.now(),
  var updatedAt: LocalDateTime? = null
) {

  fun build() = User(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    gender = gender,
    password = password,
    createdAt = createdAt,
    updatedAt = updatedAt
  )

  companion object {
    fun build(block:(UserBuilder.() -> Unit)? = null) = when(block) {
      null -> UserBuilder().build()
      else -> UserBuilder().apply(block).build()
    }
  }
}