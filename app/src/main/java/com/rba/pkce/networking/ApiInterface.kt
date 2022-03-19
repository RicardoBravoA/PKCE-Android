package com.rba.pkce.networking

import com.rba.pkce.model.movie.Movie
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("movie")
    suspend fun movie(): Response<Movie>

}