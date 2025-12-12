package com.example.financeapp.ui.transaction.preview

import com.example.financeapp.data.local.entity.TransactionEntity
import com.example.financeapp.data.model.TransactionType
import com.example.financeapp.data.repository.ITransactionRepository
import kotlinx.coroutines.flow.flow

class FakeTransactionRepository : ITransactionRepository {
    private val fakeList = listOf(
        TransactionEntity(
            id = 1,
            amount = 50.0,
            type = TransactionType.TREATS,
            description = "Ice cream",
            createdAt = System.currentTimeMillis() - 86400000L * 2
        ),
        TransactionEntity(
            id = 2,
            amount = 20.0,
            type = TransactionType.TRANSPORT,
            description = "Bus ticket",
            createdAt = System.currentTimeMillis() - 86400000L * 5
        ),
        TransactionEntity(
            id = 3,
            amount = 100.0,
            type = TransactionType.EATING_OUT,
            description = "Weekly groceries",
            createdAt = System.currentTimeMillis() - 86400000L * 1
        )
    )

    override suspend fun addTransaction(t: TransactionEntity) {
        // No-op for fake repository
    }

    override fun listTransactions() = flow {
        emit(fakeList)
    }

    override fun getCurrentMonthTotal() = flow {
        val total = fakeList.sumOf { it.amount }
        emit(total)
    }
}