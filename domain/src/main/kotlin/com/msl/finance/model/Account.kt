package com.msl.finance.model

import java.math.BigDecimal

data class Account(
  val id: Long,
  val userId: String,
  val balance: MonetaryValue
)