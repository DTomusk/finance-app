package com.example.financeapp.ui.transaction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.theme.FinanceAppTheme

@Composable
fun TransactionForm(modifier: Modifier = Modifier) {
    var amount by remember { mutableStateOf("") }
    var submittedTransaction by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Text(
            text = "What have you bought this time?",
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount (£)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (amount.isNotBlank()) {
                    submittedTransaction = "Submitted £$amount"
                    amount = "" // optional: clear field
                } else {
                    submittedTransaction = "Please enter a valid amount"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit purchase")
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (submittedTransaction.isNotBlank()) {
            Text(
                text = submittedTransaction,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionFormPreview() {
    FinanceAppTheme()
    {
        TransactionForm()
    }
}
