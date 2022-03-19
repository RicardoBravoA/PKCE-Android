package com.rba.pkce.repository

import com.rba.pkce.model.request.transaction.TransactionRequest
import com.rba.pkce.model.response.error.ErrorModel
import com.rba.pkce.model.response.transaction.Transaction
import com.rba.pkce.util.ResultType

interface TransactionRepository {

    suspend fun pay(request: TransactionRequest): ResultType<Transaction, ErrorModel>

}