package com.example.incomeexpensetracker.ui.account
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.incomeexpensetracker.data.repository.AccountRepository
import com.example.incomeexpensetracker.data.model.Account
import kotlinx.coroutines.launch

class AccountViewModel (private val repository: AccountRepository) : ViewModel() {

    val allAccount: LiveData<List<Account>> = repository.allAccount

    fun insert(account: Account) = viewModelScope.launch {
        repository.insert(account)
    }
    fun update(account: Account) = viewModelScope.launch {
        repository.update(account)
    }
    fun delete(account: Account) = viewModelScope.launch {
        repository.delete(account)
    }

}

class WordViewModelFactory(private val repository: AccountRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AccountViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}