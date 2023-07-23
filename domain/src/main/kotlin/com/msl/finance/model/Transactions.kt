package com.msl.finance.model

import com.msl.finance.model.emun.TypeTransaction
import java.time.LocalDateTime

data class Transactions(
  val id: String,
  val type: TypeTransaction,
  val value: MonetaryValue,
  val dataHora: LocalDateTime,
  val accountId: Long
)