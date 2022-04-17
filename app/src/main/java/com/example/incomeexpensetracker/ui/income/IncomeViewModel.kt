package com.example.incomeexpensetracker.ui.income

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.incomeexpensetracker.data.model.Account
import com.example.incomeexpensetracker.data.model.Category
import com.example.incomeexpensetracker.data.model.Income
import com.example.incomeexpensetracker.data.model.IncomeWithRelations
import com.example.incomeexpensetracker.data.repository.IncomeRepository
import com.example.incomeexpensetracker.utils.AppDateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IncomeViewModel @Inject constructor(
    private val incomeRepository: IncomeRepository
) : ViewModel() {

    val id: MutableState<Int> = mutableStateOf(0)
    private val selectedIncome: MutableState<Income?> = mutableStateOf(null)
    val category: MutableState<Category?> = mutableStateOf(null)
    val account: MutableState<Account?> = mutableStateOf(null)
    val amount: MutableState<String> = mutableStateOf("")

    // << All Incomes
    private val _allIncomes = MutableStateFlow<List<Income>>(emptyList())
    val allIncomes = _allIncomes
    fun getAllIncomes() {
        viewModelScope.launch {
            incomeRepository.allIncome.collect {
                _allIncomes.value = it
            }
        }
    }
    // All Incomes >>

    // << All Incomes with Relations
    private val _allIncomesWithRelations = MutableStateFlow<List<IncomeWithRelations>>(emptyList())
    val allIncomesWithRelations = _allIncomesWithRelations
    fun getAllIncomesWithRelations() {
        viewModelScope.launch {
            incomeRepository.allIncomeWithRelations.collect {
                _allIncomesWithRelations.value = it
            }
        }
    }
    // All Incomes >>

    //  << Get Income with relation By Id
    private val _selectedIncomeWithRelation = MutableStateFlow<IncomeWithRelations?>(null)
    val selectedIncomeWithRelation = _selectedIncomeWithRelation
    fun getIncomeByIdWithRelation(id: Int) {
        viewModelScope.launch {
            incomeRepository.getIncomeByIdWithRelation(id).collect { income ->
                _selectedIncomeWithRelation.value = income
            }
        }
    }

    fun updateIncomeFields(selectedIncomeWithRelation: IncomeWithRelations?) {
        if (selectedIncomeWithRelation != null) {
            id.value = selectedIncomeWithRelation.income.id
            selectedIncome.value = selectedIncomeWithRelation.income
            category.value = selectedIncomeWithRelation.category
            account.value = selectedIncomeWithRelation.account
            amount.value = selectedIncomeWithRelation.income.amount
        } else {
            id.value = 0
            selectedIncome.value = null
            category.value = null
            account.value = null
            amount.value = ""
        }
    }
    // Get Income By Id >>

    // << Insert
    private suspend fun insertIncome() {
        viewModelScope.launch { Dispatchers.IO }
        //date time
        val appDateTime = AppDateTime()
        val income = Income(
            id = 0,
            category_id = category.value?.id ?: 0,
            account_id = account.value?.id ?: 0,
            amount = amount.value,
            date = appDateTime.date,
            month = appDateTime.month,
            year = appDateTime.year
        )
        incomeRepository.insert(income)
    }

    fun storeIncome() = viewModelScope.launch {
        insertIncome()
    }
    // Insert >>

    // << Update
    private suspend fun _updateIncome() {
        viewModelScope.launch { Dispatchers.IO }
        if (selectedIncome.value !== null) {
            val income = Income(
                id = id.value,
                category_id = category.value?.id ?: 0,
                account_id = account.value?.id ?: 0,
                amount = amount.value,
                date = selectedIncome.value!!.date,
                month = selectedIncome.value!!.month,
                year = selectedIncome.value!!.year
            )
            incomeRepository.update(income)
        }
    }

    fun updateIncome() = viewModelScope.launch {
        _updateIncome()
    }
    // Update >>

    // << Delete
    private suspend fun _deleteIncome() {
        viewModelScope.launch { Dispatchers.IO }
        if (selectedIncome.value !== null) {
            val income = Income(
                id = id.value,
                category_id = selectedIncome.value!!.category_id,
                account_id = selectedIncome.value!!.account_id,
                amount = selectedIncome.value!!.amount,
                date = selectedIncome.value!!.date,
                month = selectedIncome.value!!.month,
                year = selectedIncome.value!!.year
            )
            incomeRepository.delete(income)
        }
    }

    fun deleteIncome() = viewModelScope.launch {
        _deleteIncome()
    }
    // Delete >>

}