package com.rba.pkce.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rba.pkce.datasource.MovieDataSource
import com.rba.pkce.datasource.TransactionDataSource

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val movieDataSource = MovieDataSource()
            val transactionDataSource = TransactionDataSource()
            return MainViewModel(movieDataSource, transactionDataSource) as T
        }
        throw IllegalArgumentException("Unable to construct view model")
    }
}