package com.rba.pkce.datasource

import com.rba.pkce.model.error.ErrorModel
import com.rba.pkce.model.movie.Movie
import com.rba.pkce.repository.MovieRepository
import com.rba.pkce.networking.ApiManager
import com.rba.pkce.util.ResultType
import com.rba.pkce.util.RetrofitErrorUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDataSource(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieRepository {

    override suspend fun get(): ResultType<List<Movie>, ErrorModel> {
        return withContext(dispatcher) {
            try {
                val response = ApiManager.get().movie()
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