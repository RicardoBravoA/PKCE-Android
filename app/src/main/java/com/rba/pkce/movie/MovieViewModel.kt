package com.rba.pkce.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rba.pkce.model.error.ErrorModel
import com.rba.pkce.model.movie.Movie
import com.rba.pkce.repository.MovieRepository
import com.rba.pkce.util.ResultType
import com.rba.pkce.util.SingleEvent
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieRepository: MovieRepository
): ViewModel() {

    private val _data = MutableLiveData<SingleEvent<List<Movie>>>()
    val data: LiveData<SingleEvent<List<Movie>>>
        get() = _data

    private val _error = MutableLiveData<SingleEvent<String>>()
    val error: LiveData<SingleEvent<String>>
        get() = _error

    private val _apiError = MutableLiveData<SingleEvent<ErrorModel>>()
    val apiError: LiveData<SingleEvent<ErrorModel>>
        get() = _apiError

    fun get() {
        viewModelScope.launch {
            try {
                when (val result = movieRepository.get()) {
                    is ResultType.Success -> {
                        _data.value = SingleEvent(result.value)
                    }
                    is ResultType.Error -> {
                        _apiError.value = SingleEvent(result.value)
                    }
                }
            } catch (e: Exception) {
                _error.value = SingleEvent(e.localizedMessage ?: "I'm an error")
            }
        }
    }
}