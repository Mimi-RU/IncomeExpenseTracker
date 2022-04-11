package com.example.incomeexpensetracker.data.source.local

import androidx.room.*
import com.example.incomeexpensetracker.data.model.Expense
import com.example.incomeexpensetracker.data.model.ExpenseWithRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Transaction
    @Query("SELECT * FROM expenses ORDER BY id DESC")
    fun getExpenses(): Flow<List<ExpenseWithRelation>>

    @Query("SELECT * FROM expenses WHERE id = :id")
    fun getExpenseById(id: Int): Flow<Expense>

    @Query("SELECT SUM(amount) as total FROM expenses")
    fun getTotalExpense() : Float

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(Expense: Expense)

    @Update
    suspend fun updateExpense(Expense: Expense)

    @Delete
    suspend fun deleteExpense(Expense: Expense)
}
