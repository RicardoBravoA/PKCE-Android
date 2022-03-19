package com.rba.pkce.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rba.pkce.datasource.MovieDataSource

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            val movieDataSource = MovieDataSource()
            return MovieViewModel(movieDataSource) as T
        }
        throw IllegalArgumentException("Unable to construct view model")
    }
}