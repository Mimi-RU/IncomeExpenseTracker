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
class AccountViewModel @Inject constructor (private val accountRepository: AccountRepository) : ViewModel() {


    val id : MutableState<Int> = mutableStateOf(0)
    val type : MutableState<String> = mutableStateOf("")
    val name: MutableState<String> = mutableStateOf("")
    val balance : MutableState<String> = mutableStateOf("")

    private  val  _allAccounts = MutableStateFlow<List<Account>>(emptyList())

    val allAccounts = _allAccounts

    fun getAllAccounts(){
        viewModelScope.launch {
            accountRepository.allAccount.collect() {
                _allAccounts.value = it
            }
        }
    }

    private suspend fun insertAccount(){
        viewModelScope.launch { Dispatchers.IO }
        val account = Account(
            id = 0,
            name = name.value,
            type = type.value,
            balance = balance.value
        )
        accountRepository.insert(account);
    }

    fun storeAccount() = viewModelScope.launch {
        insertAccount()
    }

}