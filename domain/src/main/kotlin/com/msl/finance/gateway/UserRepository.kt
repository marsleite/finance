package com.msl.finance.gateway

import com.msl.finance.model.User

interface UserRepository {

    suspend fun registerUser(user: User): User
}