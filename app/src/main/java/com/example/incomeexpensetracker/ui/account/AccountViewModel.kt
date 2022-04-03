package com.example.incomeexpensetracker.ui.account

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.incomeexpensetracker.data.model.Account
import com.example.incomeexpensetracker.data.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val accountRepository: AccountRepository) :
    ViewModel() {

    val id: MutableState<Int> = mutableStateOf(0)
    val type: MutableState<String> = mutableStateOf("")
    val name: MutableState<String> = mutableStateOf("")
    val balance: MutableState<String> = mutableStateOf("")

    // << All Accounts
    private val _allAccounts = MutableStateFlow<List<Account>>(emptyList())

    val allAccounts = _allAccounts

    fun getAllAccounts() {
        viewModelScope.launch {
            accountRepository.allAccount.collect {
                _allAccounts.value = it
            }
        }
    }
    // All Accounts >>

    //  << Get Account By Id
    private val _selectedAccount = MutableStateFlow<Account?>(null)

    val selectedAccount = _selectedAccount

    fun getAccountById(id: Int) {
        viewModelScope.launch {
            accountRepository.getAccountById(id).collect { account ->
                selectedAccount.value = account
            }
        }
    }

    fun updateAccountFields(selectedAccount: Account?) {
        if (selectedAccount != null) {
            id.value = selectedAccount.id
            name.value = selectedAccount.name
            type.value = selectedAccount.type
            balance.value = selectedAccount.balance
        } else {
            id.value = 0
            name.value = ""
            type.value = ""
            balance.value = ""
        }
    }
    // Get Account By Id >>

    // << Insert
    private suspend fun insertAccount() {
        viewModelScope.launch { Dispatchers.IO }
        val account = Account(
            id = 0,
            name = name.value,
            type = type.value,
            balance = balance.value
        )
        accountRepository.insert(account)
    }

    fun storeAccount() = viewModelScope.launch {
        insertAccount()
    }
    // Insert >>

    // << Update
    private suspend fun _updateAccount() {
        viewModelScope.launch { Dispatchers.IO }
        val account = Account(
            id = id.value,
            name = name.value,
            type = type.value,
            balance = balance.value
        )
        accountRepository.update(account)
    }

    fun updateAccount() = viewModelScope.launch {
        _updateAccount()
    }
    // Update >>

    // << Delete
    private suspend fun _deleteAccount() {
        viewModelScope.launch { Dispatchers.IO }
        val account = Account(
            id = id.value,
            name = name.value,
            type = type.value,
            balance = balance.value
        )
        accountRepository.delete(account)
    }

    fun deleteAccount() = viewModelScope.launch {
        _deleteAccount()
    }
    // Delete >>

}