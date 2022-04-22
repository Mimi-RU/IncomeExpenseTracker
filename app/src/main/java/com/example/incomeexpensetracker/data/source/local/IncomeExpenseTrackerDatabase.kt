package com.example.incomeexpensetracker.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.incomeexpensetracker.data.model.*

@Database(
    entities = [Account::class, Category::class, Expense::class, Income::class, Note::class, Schedule::class, PendingTransaction::class, Budget::class ],
    version = 1,
    exportSchema = false
)
abstract class IncomeExpenseTrackerDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun incomeDao(): IncomeDao
    abstract fun noteDao(): NoteDao
    abstract fun scheduleDao(): ScheduleDao
    abstract fun pendingTransactionDao():PendingTransactionDao
    abstract fun budgetDao():BudgetDao
}