package com.msl.finance.resource.repository.sql.impl

import com.msl.finance.repository.Transaction
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class TransactionImpl: Transaction {
    @Transactional
    override suspend fun <T> open(block: suspend () -> T) = block()
}