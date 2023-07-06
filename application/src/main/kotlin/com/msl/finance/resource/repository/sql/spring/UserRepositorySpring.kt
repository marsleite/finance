package com.msl.finance.resource.repository.sql.spring

import com.msl.finance.resource.repository.entity.UserEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserRepositorySpring: CoroutineCrudRepository<UserEntity, Long> {
}