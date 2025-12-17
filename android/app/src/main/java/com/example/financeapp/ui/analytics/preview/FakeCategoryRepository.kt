package com.example.financeapp.ui.analytics.preview

import com.example.financeapp.data.repository.ICategoryRepository

class FakeCategoryRepository: ICategoryRepository {
    override fun getAllCategories() = kotlinx.coroutines.flow.flowOf(
        listOf(
            com.example.financeapp.data.local.entity.CategoryEntity(
                key = "treats",
                name = "Treats",
                color = "#FF6384"
            ),
            com.example.financeapp.data.local.entity.CategoryEntity(
                key = "eating_out",
                name = "Eating Out",
                color = "#36A2EB"
            ),
            com.example.financeapp.data.local.entity.CategoryEntity(
                key = "transport",
                name = "Transport",
                color = "#FFCE56"
            ),
            com.example.financeapp.data.local.entity.CategoryEntity(
                key = "clothing",
                name = "Clothing",
                color = "#4BC0C0"
            )
        )
    )
}