package com.msl.finance.resource

import com.msl.finance.repository.Transaction

class TransactionMock: Transaction {
  override suspend fun <T> open(block: suspend () -> T) = block()
}