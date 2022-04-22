package com.example.incomeexpensetracker.data.source.local

import androidx.room.*
import com.example.incomeexpensetracker.data.model.Budget
import com.example.incomeexpensetracker.data.model.BudgetWithRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {

    @Transaction
    @Query("SELECT * FROM budgets ORDER BY id DESC")
    fun getFlowOfBudgetsWithRelation(): Flow<List<BudgetWithRelation>>

    @Query("SELECT * FROM budgets WHERE id = :id")
    fun getBudgetById(id: Int): Flow<Budget>

    @Transaction
    @Query("SELECT * FROM budgets WHERE id = :id")
    fun getBudgetWithRelationById(id: Int): Flow<BudgetWithRelation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBudget(budget: Budget)

    @Update
    suspend fun updateBudget(budget: Budget)

    @Delete
    suspend fun deleteBudget(budget: Budget)
}