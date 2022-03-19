package com.rba.pkce.networking

import com.rba.pkce.model.request.transaction.TransactionRequest
import com.rba.pkce.model.response.movie.Movie
import com.rba.pkce.model.response.transaction.Transaction
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("movie")
    suspend fun movie(): Response<List<Movie>>

    @POST("transaction")
    suspend fun pay(@Body transaction: TransactionRequest): Response<Transaction>

}