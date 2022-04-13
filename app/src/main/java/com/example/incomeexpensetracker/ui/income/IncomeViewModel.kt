package com.example.incomeexpensetracker.ui.income

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.incomeexpensetracker.data.model.Account
import com.example.incomeexpensetracker.data.model.Category
import com.example.incomeexpensetracker.data.model.Income
import com.example.incomeexpensetracker.data.model.IncomeWithRelations
import com.example.incomeexpensetracker.data.repository.AccountRepository
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
    private val incomeRepository: IncomeRepository,
    private val accountRepository: AccountRepository
    ) :
    ViewModel() {

    val id: MutableState<Int> = mutableStateOf(0)
    val category : MutableState<Category?> = mutableStateOf(null)
    val account : MutableState<Account?> = mutableStateOf(null)
    val amount: MutableState<String> = mutableStateOf("")
    val date: MutableState<String> = mutableStateOf("")

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

    // << All Incomes
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

    //  << Get Income By Id
    private val _selectedIncome = MutableStateFlow<Income?>(null)

    val selectedIncome = _selectedIncome

    fun getIncomeById(id: Int) {
        viewModelScope.launch {
            incomeRepository.getIncomeById(id).collect { income ->
                _selectedIncome.value = income
            }
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
            amount = amount.value ,
            date = appDateTime.date ,
            month = appDateTime.month ,
            year =  appDateTime.year
        )
         incomeRepository.insert(income)

        // update account
        if (account.value != null){
            val  updatedAccount = Account(
                id = account.value!!.id,
                name = account.value!!.name,
                type = account.value!!.type,
                balance = account.value!!.balance + amount.value.toDouble()
            )
            accountRepository.update(updatedAccount)
        }
    }

    fun storeIncome() = viewModelScope.launch {
        insertIncome()
    }
    // Insert >>

    // << Update
    private suspend fun _updateIncome() {
        viewModelScope.launch { Dispatchers.IO }
        val appDateTime = AppDateTime()
        val income = Income(
            id = id.value,
            category_id = category.value?.id ?: 0,
            account_id = account.value?.id ?: 0,
            amount = amount.value ,
            date = appDateTime.date ,
            month = appDateTime.month ,
            year =  appDateTime.year
        )
        incomeRepository.update(income)
    }

    fun updateIncome() = viewModelScope.launch {
        _updateIncome()
    }
    // Update >>

    // << Delete
    private suspend fun _deleteIncome() {
        viewModelScope.launch { Dispatchers.IO }
        val appDateTime = AppDateTime()
        val income = Income(
            id = id.value,
            category_id = category.value?.id ?: 0,
            account_id = account.value?.id ?: 0,
            amount = amount.value ,
            date = date.value ,
            month = appDateTime.month ,
            year =  appDateTime.year
        )
        incomeRepository.delete(income)
    }

    fun deleteIncome() = viewModelScope.launch {
        _deleteIncome()
    }
    // Delete >>

}