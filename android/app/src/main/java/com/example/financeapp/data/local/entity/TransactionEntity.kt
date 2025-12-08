package com.example.financeapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.financeapp.data.model.TransactionType

@Entity(tableName = "transactions")
data class TransactionEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val amount: Double,
    val type: TransactionType,
    val description: String?,
    val createdAt: Long = System.currentTimeMillis()
)