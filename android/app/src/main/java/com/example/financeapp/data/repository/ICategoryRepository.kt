package com.example.financeapp.data.repository

import com.example.financeapp.data.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface ICategoryRepository {
    fun getAllCategories(): Flow<List<CategoryEntity>>
}