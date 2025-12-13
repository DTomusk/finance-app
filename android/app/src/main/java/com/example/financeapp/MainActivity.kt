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
import com.example.financeapp.data.repository.TransactionRepository
import com.example.financeapp.ui.transaction.TransactionViewModel
import com.example.financeapp.ui.transaction.TransactionViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private lateinit var viewModel: TransactionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getDatabase(this)
        val repo = TransactionRepository(db.transactionDao())
        val factory = TransactionViewModelFactory(repo)

        viewModel = ViewModelProvider(this, factory)[TransactionViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            FinanceAppTheme {
                FinanceApp(viewModel)
            }
        }
    }
}