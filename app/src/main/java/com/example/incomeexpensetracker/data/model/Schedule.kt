package com.example.incomeexpensetracker.data.model

import androidx.room.*

@Entity(
    tableName = "schedules",
    foreignKeys = [
        ForeignKey(
            entity = Account::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("account_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class Schedule(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "account_id") val account_id: Int,
    @ColumnInfo(name = "amount") val amount: String,
    @ColumnInfo(name = "interval_unit") val interval_unit: String,
    @ColumnInfo(name = "repeat") val repeat: Int,
    @ColumnInfo(name = "repeat_count") val repeat_count: Int = 0,
    @ColumnInfo(name = "time") val time: String? = null
)

data class ScheduleWithRelation(
    @Embedded
    val schedule: Schedule,
    @Relation(
        parentColumn = "account_id",
        entityColumn = "id"
    )
    val account: Account
)
