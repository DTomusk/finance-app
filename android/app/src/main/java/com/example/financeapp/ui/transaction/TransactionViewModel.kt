package com.example.financeapp.ui.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.data.local.entity.TransactionEntity
import com.example.financeapp.data.repository.ICategoryRepository
import com.example.financeapp.data.repository.ITransactionRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

open class TransactionViewModel(
    private val repo: ITransactionRepository,
    private val categoryRepo: ICategoryRepository
) : ViewModel() {

    open fun addTransaction(amount: Double, type: String, description: String?) {
        viewModelScope.launch {
            repo.addTransaction(
                TransactionEntity(
                    amount = amount,
                    type = type,
                    description = description,
                    createdAt = System.currentTimeMillis()
                )
            )
        }
    }

    val transactions = repo.listTransactions()

    val monthTotal = repo.getCurrentMonthTotal()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    val categories = categoryRepo.getAllCategories()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}