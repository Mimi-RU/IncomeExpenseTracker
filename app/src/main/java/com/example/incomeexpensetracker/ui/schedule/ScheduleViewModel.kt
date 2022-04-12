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
import com.example.incomeexpensetracker.utils.AppDateTime
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
    val repeat_want: MutableState<Int> = mutableStateOf(0)
    val time: MutableState<String> = mutableStateOf("")


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
    private val _selectedSchedule = MutableStateFlow<Schedule?>(null)

    val selectedSchedule = _selectedSchedule

    fun getScheduleById(id: Int) {
        viewModelScope.launch {
            scheduleRepository.getScheduleById(id).collect { schedule ->
                selectedSchedule.value = schedule
            }
        }
    }
    // Get Schedule By Id >>

    // << Insert
    private suspend fun insertSchedule() {
        viewModelScope.launch { Dispatchers.IO }
        var appDateTime = AppDateTime()
        val schedule = Schedule(
            id = 0,
            type = type.value,
            account_id = account.value?.id ?: 0,
            category_id = category.value?.id ?: 0,
            amount = amount.value,
            interval_unit = intervalUnit.value,
            schedule_on = "",
            repeat_want = repeat_want.value,
            repeat_count = 0,
            time = time.value
        )
        scheduleRepository.insert(schedule)
    }

    fun storeSchedule() = viewModelScope.launch {
        insertSchedule()
    }
    // Insert >>

}