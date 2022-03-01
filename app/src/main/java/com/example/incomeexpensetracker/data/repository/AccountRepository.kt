package com.example.incomeexpensetracker.data.repository
import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.incomeexpensetracker.data.model.Account
import com.example.incomeexpensetracker.data.source.local.AccountDao
import com.example.incomeexpensetracker.data.source.local.IncomeExpenseTrackerDatabase

class AccountRepository  () {

    private val context = Application()

    private val accountDao: AccountDao =
        IncomeExpenseTrackerDatabase.getDatabase(context).accountDao()

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allAccount: LiveData<List<Account>> = accountDao.getAccounts()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
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