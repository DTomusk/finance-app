package com.example.financeapp.ui.transaction

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TransactionList(modifier: Modifier = Modifier, viewModel: TransactionViewModel) {
    val transactions by viewModel.transactions.collectAsStateWithLifecycle(emptyList())

    Column {
        transactions.forEach { t ->
            Text("£${t.amount} - ${t.type} - ${t.description}")
        }
    }
}