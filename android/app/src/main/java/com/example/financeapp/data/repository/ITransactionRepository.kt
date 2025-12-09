package com.example.financeapp.data.repository

import com.example.financeapp.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface ITransactionRepository {
    suspend fun addTransaction(t: TransactionEntity)
    fun listTransactions(): Flow<List<TransactionEntity>>
}
