package com.example.financeapp.data.repository

import com.example.financeapp.data.local.dao.CategoryDao
import com.example.financeapp.data.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CategoryRepository (
    private val dao: CategoryDao
) : ICategoryRepository {
    override fun getAllCategories(): Flow<List<CategoryEntity>> {
        return flowOf(
            listOf(
                CategoryEntity(
                    key = "TREATS",
                    name = "Treats",
                    color = "#FFB6C1"
                ),
                CategoryEntity(
                    key =  "EATING_OUT",
                    name = "Eating Out",
                    color = "#ADD8E6"
                ),
                CategoryEntity(
                    key = "TRANSPORT",
                    name = "Transport",
                    color = "#90EE90"
                ),
                CategoryEntity(
                    key = "CLOTHING",
                    name = "Clothing",
                    color = "#FFFFE0"
                )
            )
        )
    }
}