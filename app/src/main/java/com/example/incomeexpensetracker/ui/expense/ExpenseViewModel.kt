package com.example.incomeexpensetracker.ui.expense

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.incomeexpensetracker.data.model.Expense
import com.example.incomeexpensetracker.data.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(private val expenseRepository: ExpenseRepository) :
    ViewModel() {

    val id: MutableState<Int> = mutableStateOf(0)
    val category_id: MutableState<Int> = mutableStateOf(0)
    val account_id: MutableState<Int> = mutableStateOf(0)
    val amount: MutableState<String> = mutableStateOf("")
    val date: MutableState<String> = mutableStateOf("")
    val month: MutableState<String> = mutableStateOf("")
    val year: MutableState<String> = mutableStateOf("")

    // << All Expenses
    private val _allExpenses = MutableStateFlow<List<Expense>>(emptyList())

    val allExpense = _allExpenses

    fun getAllExpenses() {
        viewModelScope.launch {
            expenseRepository.allExpense.collect {
                _allExpenses.value = it
            }
        }
    }
    // All Expenses >>

    //  << Get Expense By Id
    private val _selectedExpense = MutableStateFlow<Expense?>(null)

    val selectedExpense = _selectedExpense

    fun getExpenseById(id: Int) {
        viewModelScope.launch {
            expenseRepository.getExpenseById(id).collect { expense ->
                selectedExpense.value = expense
            }
        }
    }

    fun updateExpenseFields(selectedExpense: Expense?) {
        if (selectedExpense != null) {
            id.value = selectedExpense.id
            category_id.value = selectedExpense.category_id
            account_id.value = selectedExpense.account_id
            amount.value = selectedExpense.amount
            date.value = selectedExpense.date
            month.value = selectedExpense.month
            year.value = selectedExpense.year
        } else {
            id.value = 0
            category_id.value = 0
            account_id.value = 0
            amount.value = ""
            date.value = ""
            month.value = ""
            year.value = ""
        }
    }
    // Get Expense By Id >>

    // << Insert
    private suspend fun insertExpense() {
        viewModelScope.launch { Dispatchers.IO }
        val expense = Expense(
            id = 0,
            category_id = category_id.value,
            account_id = account_id.value ,
            amount = amount.value ,
            date = date.value ,
            month = month.value ,
            year = year.value

        )
        expenseRepository.insert(expense)
    }

    fun storeExpense() = viewModelScope.launch {
        insertExpense()
    }
    // Insert >>

    // << Update
    private suspend fun _updateExpense() {
        viewModelScope.launch { Dispatchers.IO }
        val expense = Expense(
            id = id.value,
            category_id = category_id.value,
            account_id = account_id.value ,
            amount = amount.value ,
            date = date.value ,
            month = month.value ,
            year = year.value
        )
        expenseRepository.update(expense)
    }

    fun updateExpense() = viewModelScope.launch {
        _updateExpense()
    }
    // Update >>

    // << Delete
    private suspend fun _deleteExpense() {
        viewModelScope.launch { Dispatchers.IO }
        val expense = Expense(
            id = id.value,
            category_id = category_id.value,
            account_id = account_id.value ,
            amount = amount.value ,
            date = date.value ,
            month = month.value ,
            year = year.value
        )
        expenseRepository.delete(expense)
    }

    fun deleteExpense() = viewModelScope.launch {
        _deleteExpense()
    }
    // Delete >>


}