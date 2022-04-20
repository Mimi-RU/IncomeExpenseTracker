package com.example.incomeexpensetracker.data.repository

import com.example.incomeexpensetracker.data.model.PendingTransaction
import com.example.incomeexpensetracker.data.model.PendingTransactionWithRelation
import com.example.incomeexpensetracker.data.source.local.PendingTransactionDao
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class PendingTransactionRepository @Inject constructor(private val pendingTransactionDao: PendingTransactionDao) {

    val flowOfPendingTransactionWithRelation: Flow<List<PendingTransactionWithRelation>> =
        pendingTransactionDao.getFlowOfPendingTransactionsWithRelation()

    suspend fun getPendingTransactionById(id: Int): Flow<PendingTransaction> {
        return pendingTransactionDao.getPendingTransactionById(id)
    }

    suspend fun getPendingTransactionWithRelationById(id: Int): Flow<PendingTransactionWithRelation> {
        return pendingTransactionDao.getPendingTransactionWithRelationById(id)
    }

    suspend fun insert(pendingTransaction: PendingTransaction) {
        pendingTransactionDao.insertPendingTransaction(pendingTransaction)
    }

    suspend fun delete(pendingTransaction: PendingTransaction) {
        pendingTransactionDao.deletePendingTransaction(pendingTransaction)
    }
}