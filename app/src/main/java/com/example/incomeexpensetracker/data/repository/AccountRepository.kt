package com.example.incomeexpensetracker.data.repository
import com.example.incomeexpensetracker.data.model.Account
import com.example.incomeexpensetracker.data.source.local.AccountDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepository  @Inject constructor (private val accountDao: AccountDao) {

    val allAccount: Flow<List<Account>> = accountDao.getAccounts()

    suspend fun insert(account: Account) {
        accountDao.insertAccount(account)
    }

    suspend fun update(account: Account){
        accountDao.updateAccount(account)
    }

    suspend fun delete(account: Account){
        accountDao.deleteAccount(account)
    }
}