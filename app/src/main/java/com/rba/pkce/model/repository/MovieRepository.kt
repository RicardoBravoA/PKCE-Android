package com.rba.pkce.model.repository

import com.rba.pkce.model.error.ErrorModel
import com.rba.pkce.model.movie.Movie
import com.rba.pkce.util.ResultType

interface MovieRepository {

    suspend fun get(): ResultType<Movie, ErrorModel>

}