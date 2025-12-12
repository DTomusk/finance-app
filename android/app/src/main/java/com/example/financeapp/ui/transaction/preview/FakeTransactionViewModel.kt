package com.example.financeapp.ui.transaction.preview

import com.example.financeapp.ui.transaction.TransactionViewModel

class FakeTransactionViewModel : TransactionViewModel(
    repo = FakeTransactionRepository()
)