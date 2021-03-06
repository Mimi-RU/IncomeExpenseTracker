package com.example.incomeexpensetracker.data.repository

import com.example.incomeexpensetracker.data.model.Income
import com.example.incomeexpensetracker.data.model.IncomeWithRelations
import com.example.incomeexpensetracker.data.source.local.IncomeDao
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class IncomeRepository @Inject constructor(private val incomeDao: IncomeDao) {

    val allIncome: Flow<List<Income>> = incomeDao.getIncomes()

    val allIncomeWithRelations: Flow<List<IncomeWithRelations>> = incomeDao.getIncomeWithRelations()

    suspend fun getIncomeById(id: Int) : Flow<Income> {
        return incomeDao.getIncomeById(id)
    }
    suspend fun getIncomeByIdWithRelation(id: Int) : Flow<IncomeWithRelations>{
        return incomeDao.getIncomeByIdWithRelation(id)
    }

    suspend fun insert(income: Income) {
        incomeDao.insertIncome(income)
    }

    suspend fun update(income: Income) {
        incomeDao.updateIncome(income)
    }

    suspend fun delete(income: Income) {
        incomeDao.deleteIncome(income)
    }
}