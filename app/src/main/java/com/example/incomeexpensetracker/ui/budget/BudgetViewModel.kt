package com.example.incomeexpensetracker.ui.budget

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.incomeexpensetracker.data.model.Budget
import com.example.incomeexpensetracker.data.model.BudgetWithRelation
import com.example.incomeexpensetracker.data.model.Category
import com.example.incomeexpensetracker.data.repository.BudgetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BudgetViewModel @Inject constructor(
    private val budgetRepository: BudgetRepository
) : ViewModel() {

    val id: MutableState<Int> = mutableStateOf(0)
    val category: MutableState<Category?> = mutableStateOf(null)
    val period: MutableState<String> = mutableStateOf("")
    val amount: MutableState<String> = mutableStateOf("")


    // << All Budgets
    private val _flowOfBudgets = MutableStateFlow<List<BudgetWithRelation>>(emptyList())

    val flowOfBudgets = _flowOfBudgets

    fun getALlBudgets() {
        viewModelScope.launch {
            budgetRepository.flowOfBudgetWithRelation.collect {
                _flowOfBudgets.value = it
            }
        }
    }
    // All Budgets >>

    // get budget by ID
    private val _selectedBudgetWithRelation = MutableStateFlow<BudgetWithRelation?>(null)

    val selectedBudgetWithRelation = _selectedBudgetWithRelation

    fun getSelectedBudgetWithRelation(id: Int) {
        viewModelScope.launch {
            budgetRepository.getBudgetWithRelationById(id).collect {
                _selectedBudgetWithRelation.value = it
            }
        }
    }

    fun updateBudgetFields(selectedBudgetWithRelation: BudgetWithRelation) {
        if (selectedBudgetWithRelation !== null) {
            id.value = selectedBudgetWithRelation.budget.id
            category.value = selectedBudgetWithRelation.category
            period.value = selectedBudgetWithRelation.budget.period
            amount.value = selectedBudgetWithRelation.budget.amount.toString()
        } else {
            id.value = 0
            category.value = null
            period.value = ""
            amount.value = ""
        }
    }

    // Budget By ID >>

    // Insert
    private suspend fun insertBudget() {
        viewModelScope.launch { Dispatchers.IO }
        val budget = Budget(
            id = 0,
            category_id = category.value!!.id,
            period = period.value,
            amount = amount.value.toDouble()
        )
        budgetRepository.insert(budget = budget)
    }

    fun storeBudget() = viewModelScope.launch { insertBudget() }

    // Insert >>

    // << update
    private suspend fun _updateBudget() {
        viewModelScope.launch { Dispatchers.IO }

        val budget = Budget(
            id = id.value,
            category_id = category.value!!.id,
            period = period.value,
            amount = amount.value.toDouble()
        )
        budgetRepository.update(budget)
    }

    fun updateBudget() = viewModelScope.launch { _updateBudget() }
    // update >>

    // << Delete
    private suspend fun _deleteBudget() {
        viewModelScope.launch { Dispatchers.IO }
        val budget = Budget(
            id = id.value,
            category_id = category.value!!.id,
            period = period.value,
            amount = amount.value.toDouble()
        )
        budgetRepository.delete(budget)
    }

    fun deleteBudget() = viewModelScope.launch { _deleteBudget() }
    // Delete >>
}