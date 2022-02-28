package com.example.incomeexpensetracker.data.source.local
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.incomeexpensetracker.data.model.Income
@Dao
interface IncomeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIncome(Income: Income)

    @Query("SELECT * FROM incomes ORDER BY id DESC")
    fun getIncomes(): LiveData<List<Income>>

    @Update
    suspend fun updateIncome(Income: Income)

    @Delete
    suspend fun deleteIncome(Income: Income)
}