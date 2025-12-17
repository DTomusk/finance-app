package com.example.financeapp.ui.analytics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.financeapp.data.repository.ICategoryRepository
import com.example.financeapp.data.repository.ITransactionRepository

class AnalyticsViewModelFactory(
    private val transactionRepo: ITransactionRepository,
    private val categoryRepo: ICategoryRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnalyticsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AnalyticsViewModel(categoryRepo, transactionRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}