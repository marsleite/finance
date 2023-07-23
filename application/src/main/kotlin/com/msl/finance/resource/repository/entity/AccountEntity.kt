package com.msl.finance.resource.repository.entity

import com.msl.finance.model.Account
import com.msl.finance.model.MonetaryValue
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "account")
data class AccountEntity(
  @Id
  val id: Long,
  val userId: String,
  val balance: MonetaryValue
) {

  constructor(account: Account): this(
    id = account.id,
    userId = account.userId,
    balance = account.balance
  )

  fun toDomain() = Account(
    id = id,
    userId = userId,
    balance = balance
  )
}