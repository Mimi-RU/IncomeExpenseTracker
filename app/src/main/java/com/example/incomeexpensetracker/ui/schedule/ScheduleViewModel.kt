package com.example.incomeexpensetracker.ui.schedule

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.incomeexpensetracker.data.model.Account
import com.example.incomeexpensetracker.data.model.Category
import com.example.incomeexpensetracker.data.model.Schedule
import com.example.incomeexpensetracker.data.model.ScheduleWithRelation
import com.example.incomeexpensetracker.data.repository.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(private val scheduleRepository: ScheduleRepository) :
    ViewModel() {

    val id: MutableState<Int> = mutableStateOf(0)
    val type: MutableState<String> = mutableStateOf("")
    val account: MutableState<Account?> = mutableStateOf(null)
    val category: MutableState<Category?> = mutableStateOf(null)
    val amount: MutableState<String> = mutableStateOf("")
    val intervalUnit: MutableState<String> = mutableStateOf("")
    val notificationHour: MutableState<Int?> = mutableStateOf(0)
    val notificationDay : MutableState<String?> = mutableStateOf("Sat")
    val notificationDate : MutableState<Int?> = mutableStateOf(1)
    val notificationMonth : MutableState<String?> = mutableStateOf("Jan")


    // << All Schedules
    private val _allSchedules = MutableStateFlow<List<ScheduleWithRelation>>(emptyList())

    val allSchedule = _allSchedules

    fun getAllSchedule() {
        viewModelScope.launch {
            scheduleRepository.allSchedule.collect {
                _allSchedules.value = it
            }
        }
    }
    // All Schedules >>

    //  << Get Schedule By Id
    private val _selectedScheduleWithRelation = MutableStateFlow<ScheduleWithRelation?>(null)

    val selectedScheduleWithRelation = _selectedScheduleWithRelation

    fun getScheduleWithRelationById(id: Int) {
        viewModelScope.launch {
            scheduleRepository.getScheduleWithRelationById(id).collect { selectedScheduleWithRelation ->
                _selectedScheduleWithRelation.value = selectedScheduleWithRelation
            }
        }
    }

    fun updateScheduleFields(scheduleWithRelation: ScheduleWithRelation?) {
        if (scheduleWithRelation !== null && id.value == 0) {
            id.value = scheduleWithRelation.schedule.id
            type.value = scheduleWithRelation.schedule.type
            account.value = scheduleWithRelation.account
            category.value = scheduleWithRelation.category
            amount.value = scheduleWithRelation.schedule.amount
            intervalUnit.value = scheduleWithRelation.schedule.interval_unit
            notificationHour.value = scheduleWithRelation.schedule.notification_hour
            notificationDay.value = scheduleWithRelation.schedule.notification_day
            notificationDate.value = scheduleWithRelation.schedule.notification_date
            notificationMonth.value = scheduleWithRelation.schedule.notification_month
        }
    }
    // Get Schedule By Id >>

    // << Insert
    private suspend fun insertSchedule() {
        viewModelScope.launch { Dispatchers.IO }
        val schedule = Schedule(
            id = 0,
            type = type.value,
            account_id = account.value?.id ?: 0,
            category_id = category.value?.id ?: 0,
            amount = amount.value,
            interval_unit = intervalUnit.value,
            notification_hour = notificationHour.value,
            notification_day = notificationDay.value,
            notification_date = notificationDate.value,
            notification_month = notificationMonth.value,
        )
        scheduleRepository.insert(schedule)
    }

    fun storeSchedule() = viewModelScope.launch {
        insertSchedule()
    }

    // Insert >>
    // << Update
    private suspend fun _updateSchedule() {
        viewModelScope.launch { Dispatchers.IO }
        if (selectedScheduleWithRelation.value !== null) {
            val schedule = Schedule(
                id = id.value,
                type = type.value,
                account_id = account.value?.id ?: 0,
                category_id = category.value?.id ?: 0,
                amount = amount.value,
                interval_unit = intervalUnit.value,
                notification_hour = notificationHour.value,
                notification_day = notificationDay.value,
                notification_date = notificationDate.value,
                notification_month = notificationMonth.value,
            )
            scheduleRepository.update(schedule = schedule)
        }
    }

    fun updateSchedule() = viewModelScope.launch {
        _updateSchedule()
    }
    // Update >>

    // << Delete
    private suspend fun _deleteSchedule() {
        viewModelScope.launch { Dispatchers.IO }
        if (selectedScheduleWithRelation.value !== null) {
            val schedule = Schedule(
                id = id.value,
                type = type.value,
                account_id = account.value?.id ?: 0,
                category_id = category.value?.id ?: 0,
                amount = amount.value,
                interval_unit = intervalUnit.value,
                notification_hour = notificationHour.value,
                notification_day = notificationDay.value,
                notification_date = notificationDate.value,
                notification_month = notificationMonth.value,
            )
            scheduleRepository.delete(schedule = schedule)
        }
    }

    fun deleteSchedule() = viewModelScope.launch {
        _deleteSchedule()
    }
    // Delete >>
}