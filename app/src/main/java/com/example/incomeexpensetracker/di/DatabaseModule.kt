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

    fun provideAccountDao( database: IncomeExpenseTrackerDatabase ) = database.accountDao()

}