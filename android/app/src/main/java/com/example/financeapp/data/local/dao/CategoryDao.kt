package com.example.financeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.financeapp.data.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT [key], name, color FROM categories")
    fun getAllCategories(): Flow<List<CategoryEntity>>
}