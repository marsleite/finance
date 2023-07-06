package com.msl.finance.resource.repository.sql.Impl

import com.msl.finance.gateway.UserRepository
import com.msl.finance.model.User

class UserRepositoryImpl: UserRepository {
    override suspend fun registerUser(user: User): User {
        TODO("Not yet implemented")
    }
}