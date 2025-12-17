package com.example.financeapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.financeapp.data.local.dao.CategoryDao
import com.example.financeapp.data.local.dao.TransactionDao
import com.example.financeapp.data.local.entity.CategoryEntity
import com.example.financeapp.data.local.entity.TransactionEntity
import com.example.financeapp.data.local.migration.MIGRATION_1_2

// Database schema definition, if more tables are added, version needs to be bumped
@Database(entities = [TransactionEntity::class, CategoryEntity::class], version = 2)
// Extends room database which builds the actual database for us
abstract class AppDatabase : RoomDatabase() {
    // Gateway into table (here Transaction table)
    abstract fun transactionDao(): TransactionDao

    abstract fun categoryDao(): CategoryDao

    // Ensure that the database only gets created once
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Builds database if it doesn't already exist, otherwise return existing (singleton)
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "finance_database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}