package com.example.financeapp.data.repository

import com.example.financeapp.data.local.entity.TransactionEntity

interface ITransactionRepository {
    suspend fun addTransaction(t: TransactionEntity)
    suspend fun listTransactions(): List<TransactionEntity>
}
