package com.example.incomeexpensetracker.data.source.local

import androidx.room.*
import com.example.incomeexpensetracker.data.model.Income
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomeDao {

    @Query("SELECT * FROM incomes ORDER BY id DESC")
    fun getIncomes(): Flow<List<Income>>

    @Query("SELECT * FROM incomes WHERE id = :id")
    fun getIncomeById(id: Int): Flow<Income>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIncome(Income: Income)

    @Update
    suspend fun updateIncome(Income: Income)

    @Delete
    suspend fun deleteIncome(Income: Income)
}