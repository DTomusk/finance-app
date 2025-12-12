package com.example.financeapp.ui.transaction

import android.annotation.SuppressLint
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.financeapp.ui.transaction.preview.FakeTransactionViewModel

@Composable
fun TransactionList(
    modifier: Modifier = Modifier,
    viewModel: TransactionViewModel
) {
    val monthSpent by viewModel.monthTotal.collectAsStateWithLifecycle(0.0)
    val transactions by viewModel.transactions.collectAsStateWithLifecycle(emptyList())

    LazyColumn(
        modifier = modifier
            .padding(8.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            Text("Total spend this month", style = MaterialTheme.typography.titleSmall)
            Spacer(Modifier.height(8.dp))

            Text(
                text = "£${String.format("%.2f", monthSpent)}",
                style = MaterialTheme.typography.titleLarge,
                fontSize = MaterialTheme.typography.titleLarge.fontSize * 1.5f
            )
            Spacer(Modifier.height(12.dp))

            Text("Transaction log", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
        }

        items(transactions) { t ->
            TransactionItem(t)
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun PreviewTransactionList() {
    TransactionList(
        viewModel = FakeTransactionViewModel()
    )
}