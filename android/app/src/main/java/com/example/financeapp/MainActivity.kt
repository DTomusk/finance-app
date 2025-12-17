package com.example.financeapp

import FinanceApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModelProvider
import com.example.compose.FinanceAppTheme
import com.example.financeapp.data.local.AppDatabase
import com.example.financeapp.data.repository.CategoryRepository
import com.example.financeapp.data.repository.TransactionRepository
import com.example.financeapp.ui.analytics.AnalyticsViewModel
import com.example.financeapp.ui.analytics.AnalyticsViewModelFactory
import com.example.financeapp.ui.transaction.TransactionViewModel
import com.example.financeapp.ui.transaction.TransactionViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private lateinit var transactionViewModel: TransactionViewModel
    private lateinit var analyticsViewModel: AnalyticsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getDatabase(this)
        val transactionRepo = TransactionRepository(db.transactionDao())
        val categoryRepo = CategoryRepository(db.categoryDao())
        val transactionFactory = TransactionViewModelFactory(transactionRepo, categoryRepo)
        val analyticsFactory = AnalyticsViewModelFactory(transactionRepo, categoryRepo)

        transactionViewModel = ViewModelProvider(this, transactionFactory)[TransactionViewModel::class.java]
        analyticsViewModel = ViewModelProvider(this, analyticsFactory)[AnalyticsViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            FinanceAppTheme {
                FinanceApp(
                    transactionViewModel,
                    analyticsViewModel
                )
            }
        }
    }
}