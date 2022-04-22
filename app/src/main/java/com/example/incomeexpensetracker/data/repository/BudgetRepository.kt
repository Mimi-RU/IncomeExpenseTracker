package com.example.incomeexpensetracker.data.repository

import com.example.incomeexpensetracker.data.model.Budget
import com.example.incomeexpensetracker.data.model.BudgetWithRelation
import com.example.incomeexpensetracker.data.source.local.BudgetDao
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class BudgetRepository @Inject constructor(private val budgetDao: BudgetDao) {

    val flowOfBudgetWithRelation: Flow<List<BudgetWithRelation>> =
        budgetDao.getFlowOfBudgetsWithRelation()

    suspend fun getBudgetById(id: Int): Flow<Budget> {
        return budgetDao.getBudgetById(id)
    }

    suspend fun getBudgetWithRelationById(id: Int): Flow<BudgetWithRelation> {
        return budgetDao.getBudgetWithRelationById(id)
    }

    suspend fun insert(budget: Budget) {
        budgetDao.insertBudget(budget)
    }

    suspend fun update(budget: Budget) {
        budgetDao.updateBudget(budget)
    }

    suspend fun delete(budget: Budget) {
        budgetDao.deleteBudget(budget)
    }

}