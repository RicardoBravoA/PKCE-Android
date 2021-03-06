package com.rba.pkce.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rba.pkce.model.request.transaction.TransactionRequest
import com.rba.pkce.model.response.error.ErrorModel
import com.rba.pkce.model.response.movie.Movie
import com.rba.pkce.model.response.transaction.Transaction
import com.rba.pkce.repository.MovieRepository
import com.rba.pkce.repository.TransactionRepository
import com.rba.pkce.util.ResultType
import com.rba.pkce.util.SingleEvent
import kotlinx.coroutines.launch

class MainViewModel(
    private val movieRepository: MovieRepository,
    private val transactionRepository: TransactionRepository
): ViewModel() {

    private val _data = MutableLiveData<SingleEvent<List<Movie>>>()
    val data: LiveData<SingleEvent<List<Movie>>>
        get() = _data

    private val _dataTransaction = MutableLiveData<SingleEvent<Transaction>>()
    val dataTransaction: LiveData<SingleEvent<Transaction>>
        get() = _dataTransaction

    private val _error = MutableLiveData<SingleEvent<String>>()
    val error: LiveData<SingleEvent<String>>
        get() = _error

    private val _apiError = MutableLiveData<SingleEvent<ErrorModel>>()
    val apiError: LiveData<SingleEvent<ErrorModel>>
        get() = _apiError

    private val _loading = MutableLiveData<SingleEvent<Boolean>>()
    val loading: LiveData<SingleEvent<Boolean>>
        get() = _loading

    fun get() {
        _loading.value = SingleEvent(true)

        viewModelScope.launch {
            try {
                when (val result = movieRepository.get()) {
                    is ResultType.Success -> {
                        _data.value = SingleEvent(result.value)
                        _loading.value = SingleEvent(false)
                    }
                    is ResultType.Error -> {
                        _apiError.value = SingleEvent(result.value)
                        _loading.value = SingleEvent(false)
                    }
                }
            } catch (e: Exception) {
                _error.value = SingleEvent(e.localizedMessage ?: "I'm an error")
                _loading.value = SingleEvent(false)
            }
        }
    }

    fun pay(clientId: String, amount: String) {
        _loading.value = SingleEvent(true)
        val request = TransactionRequest(clientId, amount)

        viewModelScope.launch {
            try {
                when (val result = transactionRepository.pay(request)) {
                    is ResultType.Success -> {
                        _dataTransaction.value = SingleEvent(result.value)
                        _loading.value = SingleEvent(false)
                    }
                    is ResultType.Error -> {
                        _apiError.value = SingleEvent(result.value)
                        _loading.value = SingleEvent(false)
                    }
                }
            } catch (e: Exception) {
                _error.value = SingleEvent(e.localizedMessage ?: "I'm an error")
                _loading.value = SingleEvent(false)
            }
        }
    }
}