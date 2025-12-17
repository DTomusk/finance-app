package com.example.financeapp.ui.analytics

import android.R
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.financeapp.ui.analytics.preview.FakeAnalyticsViewModel
import com.example.financeapp.ui.transaction.preview.FakeTransactionRepository

@Composable
fun AnalyticsScreen(
    viewModel: AnalyticsViewModel,
    modifier: Modifier = Modifier
) {
    val chartData by viewModel.chartData
        .collectAsStateWithLifecycle(emptyList<PieChartData>())

    PieChart(data = chartData)
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun AnalyticsScreenPreview() {
    AnalyticsScreen(
        viewModel = FakeAnalyticsViewModel()
    )
}