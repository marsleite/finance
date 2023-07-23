package com.msl.finance.resource.repository.sql.impl

import com.msl.finance.gateway.AccountRepositoryGateway
import com.msl.finance.model.Account
import com.msl.finance.resource.repository.entity.AccountEntity
import com.msl.finance.resource.repository.sql.spring.AccountRepositorySpring
import org.springframework.stereotype.Component

@Component
class AccountRepositoryGateway(
  private val accountRepositorySpring: AccountRepositorySpring
) : AccountRepositoryGateway {
  override suspend fun addAccount(account: Account): Account {
    return accountRepositorySpring.save(AccountEntity(account)).toDomain()
  }
}