package com.example.incomeexpensetracker.data.repository

import com.example.incomeexpensetracker.data.model.Schedule
import com.example.incomeexpensetracker.data.model.ScheduleWithRelation
import com.example.incomeexpensetracker.data.source.local.ScheduleDao
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ScheduleRepository @Inject constructor(private val scheduleDao: ScheduleDao) {

    val allSchedule: Flow<List<ScheduleWithRelation>> = scheduleDao.getSchedules()

    suspend fun getScheduleById(id: Int): Flow<Schedule> {
        return scheduleDao.getScheduleById(id)
    }

    suspend fun insert(schedule: Schedule) {
        scheduleDao.insertSchedule(schedule)
    }

    suspend fun update(schedule: Schedule) {
        scheduleDao.updateSchedule(schedule)
    }

    suspend fun delete(schedule: Schedule) {
        scheduleDao.deleteSchedule(schedule)
    }
}