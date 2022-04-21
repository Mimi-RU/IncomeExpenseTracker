package com.example.incomeexpensetracker.ui.pending_transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.incomeexpensetracker.data.model.PendingTransactionWithRelation
import com.example.incomeexpensetracker.data.repository.PendingTransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PendingTransactionViewModel @Inject constructor(private val pendingTransactionRepository: PendingTransactionRepository) :
    ViewModel() {

    // << all pending transactions
    private val _flowOfPendingTransactions = MutableStateFlow<List<PendingTransactionWithRelation>>(
        emptyList()
    )

    val flowOfPendingTransactions = _flowOfPendingTransactions

    fun getFlowOfPendingTransactions() {
        viewModelScope.launch {
            pendingTransactionRepository.flowOfPendingTransactionWithRelation.collect {
                _flowOfPendingTransactions.value = it
            }
        }
    }

    // all pending transactions >>

    // << get pending transaction by id
    private val _pendingTransactionWithRelation = MutableStateFlow<PendingTransactionWithRelation?>(null)

    val pendingTransactionWithRelation = _pendingTransactionWithRelation

    fun getPendingTransactionWithRelation(id: Int){
        viewModelScope.launch {
            pendingTransactionRepository.getPendingTransactionWithRelationById(id).collect {
                _pendingTransactionWithRelation.value = it
            }
        }
    }

    // get pending transaction by id >>


}