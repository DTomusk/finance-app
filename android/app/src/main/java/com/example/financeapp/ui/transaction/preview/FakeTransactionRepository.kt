package com.example.financeapp.ui.transaction.preview

import com.example.financeapp.data.local.entity.TransactionEntity
import com.example.financeapp.data.repository.ITransactionRepository

class FakeTransactionRepository : ITransactionRepository {
    private val transactions = mutableListOf<TransactionEntity>()

    override suspend fun addTransaction(t: TransactionEntity) {
        transactions.add(t)
    }

    override suspend fun listTransactions(): List<TransactionEntity> {
        return transactions
    }
}
