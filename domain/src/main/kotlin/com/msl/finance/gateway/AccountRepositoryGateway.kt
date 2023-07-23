package com.msl.finance.gateway

import com.msl.finance.model.Account

interface AccountRepositoryGateway {

  suspend fun addAccount(account: Account): Account
}