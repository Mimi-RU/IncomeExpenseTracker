package com.example.incomeexpensetracker.ui.account
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.incomeexpensetracker.data.repository.AccountRepository
import com.example.incomeexpensetracker.data.model.Account
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor (private val accountRepository: AccountRepository) : ViewModel() {

    private  val  _allAccounts = MutableStateFlow<List<Account>>(emptyList())

    val allAccounts = _allAccounts

    fun getAllAccounts(){
        viewModelScope.launch {
            accountRepository.allAccount.collect() {
                _allAccounts.value = it
            }
        }
    }

}