package com.example.financeapp.ui.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.data.local.entity.TransactionEntity
import com.example.financeapp.data.model.TransactionType
import com.example.financeapp.data.repository.ITransactionRepository
import kotlinx.coroutines.launch

open class TransactionViewModel(
    private val repo: ITransactionRepository
) : ViewModel() {

    open fun addTransaction(amount: Double, type: TransactionType, description: String?) {
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
}