package com.example.incomeexpensetracker.data.source.local

import androidx.room.Database
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
    abstract fun scheduleDao():ScheduleDao
}