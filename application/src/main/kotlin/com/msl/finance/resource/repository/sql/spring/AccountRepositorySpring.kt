package com.msl.finance.resource.repository.sql.spring

import com.msl.finance.resource.repository.entity.AccountEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AccountRepositorySpring: CoroutineCrudRepository<AccountEntity, Long> {
}