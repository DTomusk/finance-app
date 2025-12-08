package com.example.financeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.financeapp.data.local.AppDatabase
import com.example.financeapp.data.repository.TransactionRepository
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.transaction.TransactionForm
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
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Dave's money") })
                    },
                ) { innerPadding ->
                    TransactionForm(
                        modifier = Modifier.padding(innerPadding),
                        viewModel)
                }
            }
        }
    }
}