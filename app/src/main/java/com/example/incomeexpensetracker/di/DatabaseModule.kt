package com.example.incomeexpensetracker.di

import android.content.Context
import androidx.room.Room
import com.example.incomeexpensetracker.data.source.local.IncomeExpenseTrackerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        IncomeExpenseTrackerDatabase::class.java,
        "income_expense_database"
    ).build()

    @Singleton
    @Provides
    fun provideAccountDao( database: IncomeExpenseTrackerDatabase ) = database.accountDao()

    @Singleton
    @Provides
    fun provideCategoryDao( database: IncomeExpenseTrackerDatabase ) = database.categoryDao()

    @Singleton
    @Provides
    fun provideExpenseDao( database: IncomeExpenseTrackerDatabase ) = database.expenseDao()

    @Singleton
    @Provides
    fun provideIncomeDao( database: IncomeExpenseTrackerDatabase ) = database.incomeDao()

    @Singleton
    @Provides
    fun provideNoteDao( database: IncomeExpenseTrackerDatabase ) = database.noteDao()

    @Singleton
    @Provides
    fun provideTagDao( database: IncomeExpenseTrackerDatabase ) = database.tagDao()

    @Singleton
    @Provides
    fun provideScheduleDao( database: IncomeExpenseTrackerDatabase ) = database.scheduleDao()


}