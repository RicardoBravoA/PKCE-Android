package com.rba.pkce.repository

import com.rba.pkce.model.response.error.ErrorModel
import com.rba.pkce.model.response.movie.Movie
import com.rba.pkce.util.ResultType

interface MovieRepository {

    suspend fun get(): ResultType<List<Movie>, ErrorModel>

}