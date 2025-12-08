package com.example.financeapp.ui.transaction.preview

import com.example.financeapp.data.model.TransactionType
import com.example.financeapp.ui.transaction.TransactionViewModel

class PreviewTransactionViewModel : TransactionViewModel(
    repo = FakeTransactionRepository()
) {
    override fun addTransaction(amount: Double, type: TransactionType, description: String?) {
    }
}