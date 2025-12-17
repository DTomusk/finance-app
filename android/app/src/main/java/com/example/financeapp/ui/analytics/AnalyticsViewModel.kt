package com.example.financeapp.ui.analytics

import android.graphics.Color.parseColor
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.financeapp.data.repository.ICategoryRepository
import com.example.financeapp.data.repository.ITransactionRepository
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

open class AnalyticsViewModel(
    private val categoryRepo: ICategoryRepository,
    private val transactionRepo: ITransactionRepository
) : ViewModel() {
    // Analytics specific logic will go here
    val chartData =
        combine(
            categoryRepo.getAllCategories(),
            transactionRepo.getTotalByType()
        ) { categories, totalsByType ->
            categories.map { category ->
                val total = totalsByType[category.key] ?: 0.0
                PieChartData(
                    category = category.name,
                    amount = total.toFloat(),
                    color = Color(parseColor(category.color))
                )
            }

    }
}