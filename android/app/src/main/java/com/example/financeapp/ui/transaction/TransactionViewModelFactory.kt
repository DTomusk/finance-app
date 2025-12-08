package com.example.financeapp.ui.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.financeapp.data.repository.TransactionRepository

class TransactionViewModelFactory(
    private val repo: TransactionRepository
) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TransactionViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}