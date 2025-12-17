package com.example.financeapp.ui.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.financeapp.data.repository.CategoryRepository
import com.example.financeapp.data.repository.TransactionRepository

class TransactionViewModelFactory(
    private val repo: TransactionRepository,
    private val categoryRepo: CategoryRepository
) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TransactionViewModel(repo, categoryRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}