package com.example.financeapp.data.local.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {

        // 1. Create new table with the NEW schema
        db.execSQL(
            """
            CREATE TABLE transactions_new (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                amount REAL NOT NULL,
                type TEXT NOT NULL,
                description TEXT,
                createdAt INTEGER NOT NULL
            )
            """
        )

        // 2. Copy data from old table
        db.execSQL(
            """
            INSERT INTO transactions_new (id, amount, type, description, createdAt)
            SELECT id, amount, type, description, createdAt FROM transactions
            """
        )

        // 3. Drop old table
        db.execSQL("DROP TABLE transactions")

        // 4. Rename new table
        db.execSQL("ALTER TABLE transactions_new RENAME TO transactions")
    }
}
