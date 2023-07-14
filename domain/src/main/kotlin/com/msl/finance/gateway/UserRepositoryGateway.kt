package com.msl.finance.gateway

import com.msl.finance.model.User

interface UserRepositoryGateway {

    suspend fun registerUser(user: User): User
}