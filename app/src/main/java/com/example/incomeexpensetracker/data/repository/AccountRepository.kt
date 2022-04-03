package com.example.incomeexpensetracker.data.repository

import com.example.incomeexpensetracker.data.model.Account
import com.example.incomeexpensetracker.data.source.local.AccountDao
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class AccountRepository @Inject constructor(private val accountDao: AccountDao) {

    val allAccount: Flow<List<Account>> = accountDao.getAccounts()

    suspend fun getAccountById(id: Int) : Flow<Account> {
        return accountDao.getAccountById(id)
    }

    suspend fun insert(account: Account) {
        accountDao.insertAccount(account)
    }

    suspend fun update(account: Account) {
        accountDao.updateAccount(account)
    }

    suspend fun delete(account: Account) {
        accountDao.deleteAccount(account)
    }
}