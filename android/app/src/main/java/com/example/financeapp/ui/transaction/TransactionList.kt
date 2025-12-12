package com.example.financeapp.ui.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TransactionList(
    modifier: Modifier = Modifier,
    viewModel: TransactionViewModel
) {
    val monthSpent by viewModel.monthTotal.collectAsStateWithLifecycle(0.0)
    val transactions by viewModel.transactions.collectAsStateWithLifecycle(emptyList())

    LazyColumn(
        modifier = modifier.padding(16.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            // Header - takes only needed space
            Text("Total spend this month", style = MaterialTheme.typography.titleSmall)
            Spacer(Modifier.height(8.dp))

            Text(
                text = "£${String.format("%.2f", monthSpent)}",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(24.dp))

            Text("Transaction log", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
        }

        items(transactions) { t ->
            TransactionItem(t)
        }
    }
}
