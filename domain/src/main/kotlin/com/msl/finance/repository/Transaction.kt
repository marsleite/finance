package com.msl.finance.repository

interface Transaction {

    suspend fun <T> open(block: suspend () -> T): T
}