package com.example.incomeexpensetracker.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.incomeexpensetracker.data.model.*

@Database(entities = [Account::class, Category::class, Expense::class, Income::class, Note::class, Tag::class], version = 1, exportSchema = false)
public  abstract class IncomeExpenseTrackerDatabase: RoomDatabase() {

    abstract fun accountDao(): AccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun expenseDao():ExpenseDao
    abstract fun incomeDao():IncomeDao
    abstract fun noteDao():NoteDao
    abstract fun tagDao():TagDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: IncomeExpenseTrackerDatabase? = null

        fun getDatabase(context: Context): IncomeExpenseTrackerDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IncomeExpenseTrackerDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}