package com.example.financeapp.data.local

import androidx.room.TypeConverter
import com.example.financeapp.data.model.TransactionType

class Converters {

    @TypeConverter
    fun fromTransactionType(value: TransactionType): String {
        return value.name
    }

    @TypeConverter
    fun toTransactionType(value: String): TransactionType {
        return TransactionType.valueOf(value)
    }
}