package com.example.financeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.financeapp.data.local.entity.TransactionEntity

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transaction: TransactionEntity)

    @Query("SELECT * FROM transactions")
    suspend fun getAll(): List<TransactionEntity>
}