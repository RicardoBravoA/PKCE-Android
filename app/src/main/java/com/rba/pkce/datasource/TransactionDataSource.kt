package com.rba.pkce.datasource

import com.rba.pkce.model.request.transaction.TransactionRequest
import com.rba.pkce.model.response.error.ErrorModel
import com.rba.pkce.model.response.transaction.Transaction
import com.rba.pkce.networking.ApiManager
import com.rba.pkce.repository.TransactionRepository
import com.rba.pkce.util.ResultType
import com.rba.pkce.util.RetrofitErrorUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TransactionDataSource(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : TransactionRepository {

    override suspend fun pay(request: TransactionRequest): ResultType<Transaction, ErrorModel> {
        return withContext(dispatcher) {
            try {
                val response = ApiManager.get().pay(request)
                if (response.isSuccessful) {
                    val data = response.body()
                    ResultType.Success(data!!)
                } else {
                    val error = RetrofitErrorUtil.parseError(response)!!
                    ResultType.Error(error)
                }
            } catch (t: Throwable) {
                ResultType.Error(ErrorModel())
            }
        }
    }
}