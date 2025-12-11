package com.example.financeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.financeapp.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transaction: TransactionEntity)

    @Query("SELECT * FROM transactions ORDER BY createdAt DESC")
    fun getAll(): Flow<List<TransactionEntity>>

    @Query(value = "SELECT SUM(amount) FROM transactions WHERE createdAt >= :startOfMonth AND createdAt <= :startOfNextMonth")
    fun getTotalForMonth(
        startOfMonth: Long,
        startOfNextMonth: Long
    ): Flow<Double?>
}