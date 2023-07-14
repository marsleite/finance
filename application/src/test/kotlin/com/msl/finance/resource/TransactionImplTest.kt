package com.msl.finance.resource

import com.msl.finance.resource.repository.sql.impl.TransactionImpl
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class TransactionImplTest {

  @Test
  fun `should process transaction with success`() = runTest {
    val transactionImpl = TransactionImpl()

    val result = transactionImpl.open { "finance" + "project" }
    Assertions.assertThat(result).isEqualTo("financeproject")
  }
}