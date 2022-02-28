package com.example.incomeexpensetracker.data.source.local
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.incomeexpensetracker.data.model.Expense

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExpense(Expense: Expense)

    @Query("SELECT * FROM expenses ORDER BY id DESC")
    fun getExpenses(): LiveData<List<Expense>>

    @Update
    suspend fun updateExpense(Expense: Expense)

    @Delete
    suspend fun deleteExpense(Expense: Expense)
}