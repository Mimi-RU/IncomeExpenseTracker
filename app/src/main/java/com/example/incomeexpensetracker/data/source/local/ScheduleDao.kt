package com.example.incomeexpensetracker.data.source.local

import androidx.room.*
import com.example.incomeexpensetracker.data.model.Schedule
import com.example.incomeexpensetracker.data.model.ScheduleWithRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Transaction
    @Query("SELECT * FROM schedules ORDER BY id DESC")
    fun getSchedules(): Flow<List<ScheduleWithRelation>>

    @Query("SELECT * FROM schedules WHERE id = :id")
    fun getScheduleById(id: Int): Flow<Schedule>

    @Transaction
    @Query("SELECT * FROM schedules WHERE id = :id")
    fun getScheduleWithRelationById(id: Int): Flow<ScheduleWithRelation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: Schedule)

    @Update
    suspend fun updateSchedule(schedule: Schedule)

    @Delete
    suspend fun deleteSchedule(schedule: Schedule)
}