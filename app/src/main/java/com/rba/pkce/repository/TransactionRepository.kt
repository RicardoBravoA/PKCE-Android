package com.rba.pkce.repository

import com.rba.pkce.model.error.ErrorModel
import com.rba.pkce.model.transaction.Transaction
import com.rba.pkce.util.ResultType

interface TransactionRepository {

    suspend fun pay(): ResultType<Transaction, ErrorModel>

}