package com.example.incomeexpensetracker.ui.expense

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.incomeexpensetracker.data.model.Account
import com.example.incomeexpensetracker.data.model.Category
import com.example.incomeexpensetracker.data.model.Expense
import com.example.incomeexpensetracker.data.model.ExpenseWithRelation
import com.example.incomeexpensetracker.data.repository.AccountRepository
import com.example.incomeexpensetracker.data.repository.ExpenseRepository
import com.example.incomeexpensetracker.utils.AppDateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository,
    private val accountRepository: AccountRepository
) :
    ViewModel() {

    val id: MutableState<Int> = mutableStateOf(0)
    val selectedExpense: MutableState<Expense?> = mutableStateOf(null)
    val category_id: MutableState<Int> = mutableStateOf(0)
    val category: MutableState<Category?> = mutableStateOf(null)
    val account_id: MutableState<Int> = mutableStateOf(0)
    val account: MutableState<Account?> = mutableStateOf(null)
    val amount: MutableState<String> = mutableStateOf("")
    val date: MutableState<String> = mutableStateOf("")
    val month: MutableState<String> = mutableStateOf("")
    val year: MutableState<String> = mutableStateOf("")

    // << All Expenses
    private val _flowOfExpenses = MutableStateFlow<List<ExpenseWithRelation>>(emptyList())

    val flowOfExpenses = _flowOfExpenses

    fun getAllExpenses() {
        viewModelScope.launch {
            expenseRepository.flowOfExpensesWithRelation.collect {
                _flowOfExpenses.value = it
            }
        }
    }
    // All Expenses >>

    //  << Get Expense By Id
    private val _selectedExpenseWithRelation = MutableStateFlow<ExpenseWithRelation?>(null)

    val selectedExpenseWithRelation = _selectedExpenseWithRelation

    fun getExpenseWithRelationById(id: Int) {
        viewModelScope.launch {
            expenseRepository.getExpenseWithRelationById(id).collect { expenseWithRelation ->
                _selectedExpenseWithRelation.value = expenseWithRelation
            }
        }
    }

    fun updateExpenseFields(selectedExpenseWithRelation: ExpenseWithRelation) {
        if (selectedExpenseWithRelation != null) {
            id.value = selectedExpenseWithRelation.expense.id
            selectedExpense.value = selectedExpenseWithRelation.expense
            category.value = selectedExpenseWithRelation.category
            account.value = selectedExpenseWithRelation.account
            amount.value = selectedExpenseWithRelation.expense.amount
        } else {
            id.value = 0
            selectedExpense.value = null
            category.value = null
            account.value = null
            amount.value = ""
        }
    }
    // Get Expense By Id >>

    // << Insert
    private suspend fun insertExpense() {
        viewModelScope.launch { Dispatchers.IO }
        var appDateTime = AppDateTime()
        val expense = Expense(
            id = 0,
            category_id = category.value?.id ?: 0,
            account_id = account.value?.id ?: 0,
            amount = amount.value,
            date = appDateTime.date,
            month = appDateTime.month,
            year = appDateTime.year
        )
        expenseRepository.insert(expense)

        // update account balance
        if (account.value !== null) {
            val updatedAccount = Account(
                id = account.value!!.id,
                name = account.value!!.name,
                type = account.value!!.type,
                balance = account.value!!.balance - amount.value.toDouble()
            )
            accountRepository.update(updatedAccount)
        }
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
            category_id = category.value!!.id,
            account_id = account.value!!.id,
            amount = amount.value,
            date = selectedExpense.value!!.date,
            month = selectedExpense.value!!.month,
            year = selectedExpense.value!!.year
        )
        expenseRepository.update(expense)

        // update account balance
        if (account.value !== null) {
            val amountDiff = selectedExpense.value!!.amount.toDouble() - amount.value.toDouble()
            val updatedAccount = Account(
                id = account.value!!.id,
                name = account.value!!.name,
                type = account.value!!.type,
                balance = account.value!!.balance + amountDiff
            )
            accountRepository.update(updatedAccount)
        }
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
            account_id = account_id.value,
            amount = amount.value,
            date = date.value,
            month = month.value,
            year = year.value
        )
        expenseRepository.delete(expense)

        // update account balance
        if (account.value !== null) {
            val amountDiff = selectedExpense.value!!.amount.toDouble()
            val updatedAccount = Account(
                id = account.value!!.id,
                name = account.value!!.name,
                type = account.value!!.type,
                balance = account.value!!.balance + amountDiff
            )
            accountRepository.update(updatedAccount)
        }
    }

    fun deleteExpense() = viewModelScope.launch {
        _deleteExpense()
    }
    // Delete >>

}