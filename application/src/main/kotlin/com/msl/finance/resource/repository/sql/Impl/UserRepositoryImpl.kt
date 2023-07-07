package com.msl.finance.resource.repository.sql.Impl

import com.msl.finance.gateway.UserRepository
import com.msl.finance.model.User
import com.msl.finance.resource.repository.entity.UserEntity
import com.msl.finance.resource.repository.sql.spring.UserRepositorySpring
import org.springframework.stereotype.Component

@Component
class UserRepositoryImpl(
  private val userRepositorySpring: UserRepositorySpring
): UserRepository {
  override suspend fun registerUser(user: User): User {
    return userRepositorySpring.save(UserEntity(user)).toDomain()
  }

}