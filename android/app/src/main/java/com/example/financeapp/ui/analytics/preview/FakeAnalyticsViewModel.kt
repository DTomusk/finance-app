package com.example.financeapp.ui.analytics.preview

import com.example.financeapp.ui.analytics.AnalyticsViewModel
import com.example.financeapp.ui.transaction.preview.FakeTransactionRepository

class FakeAnalyticsViewModel : AnalyticsViewModel(
    categoryRepo = FakeCategoryRepository(),
    transactionRepo = FakeTransactionRepository()
)