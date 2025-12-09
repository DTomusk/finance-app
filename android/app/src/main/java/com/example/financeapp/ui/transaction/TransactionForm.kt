package com.example.financeapp.ui.transaction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
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
import com.example.financeapp.data.model.TransactionType
import com.example.financeapp.ui.theme.FinanceAppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TransactionForm(modifier: Modifier = Modifier, viewModel: TransactionViewModel) {
    var amount by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf(TransactionType.TREATS)}
    var submittedTransaction by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var description by remember { mutableStateOf( "" ) }

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
            onValueChange = { newValue ->
                val cleaned = newValue.replace(",", ".")
                if (cleaned.matches(Regex("""\d*\.?\d{0,2}"""))) {
                    amount = cleaned
                }
            },
            label = { Text("Amount (£)") },
            placeholder = { Text("Enter amount in GBP")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedType.name,
                onValueChange = {},
                label = { Text("Transaction type") },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                TransactionType.entries.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type.name) },
                        onClick = {
                            selectedType = type
                            expanded = false
                        }
                    )

                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description (optional)") },
            placeholder = { Text("Optionally enter a description of the purchase")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val amountDouble = amount.toDoubleOrNull()
                if (amountDouble != null) {
                    viewModel.addTransaction(amountDouble, selectedType, description)
                    submittedTransaction = "Submitted £$amountDouble as ${selectedType.name} with description $description."
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
