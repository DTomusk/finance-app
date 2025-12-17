package com.example.financeapp.data.repository

import com.example.financeapp.data.local.dao.TransactionDao
import com.example.financeapp.data.local.entity.TransactionEntity
import com.example.financeapp.utils.getMonthBounds
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Abstraction layer so ui doesn't have to know about daos
// Also makes it easier to replace implementation (e.g. use other db) without having to change ui
class TransactionRepository(
    private val dao: TransactionDao
) : ITransactionRepository {
    override suspend fun addTransaction(t: TransactionEntity) = dao.insert(t)
    override fun listTransactions() = dao.getAll()
    override fun getCurrentMonthTotal(): Flow<Double> {
        val (start, end) = getMonthBounds()
        return dao.getTotalForMonth(start, end)
            .map { it ?: 0.0 }
    }
    override fun getTotalByType() = dao.getTotalByType()
}