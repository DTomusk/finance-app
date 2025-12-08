package com.example.financeapp.data.repository

import com.example.financeapp.data.local.dao.TransactionDao
import com.example.financeapp.data.local.entity.TransactionEntity

// Abstraction layer so ui doesn't have to know about daos
// Also makes it easier to replace implementation (e.g. use other db) without having to change ui
class TransactionRepository(
    private val dao: TransactionDao
) : ITransactionRepository {
    override suspend fun addTransaction(t: TransactionEntity) = dao.insert(t)
    override suspend fun listTransactions() = dao.getAll()
}