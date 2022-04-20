package com.example.incomeexpensetracker.data.source.local

import androidx.room.*
import com.example.incomeexpensetracker.data.model.PendingTransactionWithRelation
import com.example.incomeexpensetracker.data.model.PendingTransaction
import kotlinx.coroutines.flow.Flow

@Dao
interface PendingTransactionDao {
    @Transaction
    @Query("SELECT * FROM pending_transactions ORDER BY id DESC")
    fun getFlowOfPendingTransactionsWithRelation(): Flow<List<PendingTransactionWithRelation>>

    @Query("SELECT * FROM pending_transactions WHERE id = :id")
    fun getPendingTransactionById(id: Int): Flow<PendingTransaction>

    @Transaction
    @Query("SELECT * FROM pending_transactions WHERE id = :id")
    fun getPendingTransactionWithRelationById(id: Int): Flow<PendingTransactionWithRelation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPendingTransaction(pendingTransaction: PendingTransaction)

    @Delete
    suspend fun deletePendingTransaction(pendingTransaction: PendingTransaction)
}