package com.example.incomeexpensetracker.data.repository

import com.example.incomeexpensetracker.data.model.Expense
import com.example.incomeexpensetracker.data.model.ExpenseWithRelation
import com.example.incomeexpensetracker.data.source.local.ExpenseDao
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped

class ExpenseRepository @Inject constructor(private val expenseDao: ExpenseDao) {

    val flowOfExpensesWithRelation: Flow<List<ExpenseWithRelation>> =
        expenseDao.getFlowOfExpensesWithRelation()

    val flowOfExpenses: Flow<List<Expense>> = expenseDao.getFlowOfExpenses()

    suspend fun getExpenseById(id: Int): Flow<Expense> {
        return expenseDao.getExpenseById(id)
    }

    suspend fun getExpenseWithRelationById(id: Int): Flow<ExpenseWithRelation> {
        return expenseDao.getExpenseWithRelationById(id)
    }

    suspend fun insert(expense: Expense) {
        expenseDao.insertExpense(expense)
    }

    suspend fun update(expense: Expense) {
        expenseDao.updateExpense(expense)
    }

    suspend fun delete(expense: Expense) {
        expenseDao.deleteExpense(expense)
    }
}