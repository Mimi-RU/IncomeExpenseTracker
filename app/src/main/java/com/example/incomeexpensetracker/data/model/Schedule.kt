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
        ),

        ForeignKey(
            entity = Category::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("category_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class Schedule(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "account_id") val account_id: Int,
    @ColumnInfo(name = "category_id") val category_id: Int,
    @ColumnInfo(name = "amount") val amount: String,
    @ColumnInfo(name = "interval_unit") val interval_unit: String,
    @ColumnInfo(name = "notification_hour") val notification_hour: Int?,
    @ColumnInfo(name = "notification_day") val notification_day: String?,
    @ColumnInfo(name = "notification_date") val notification_date: Int?,
    @ColumnInfo(name = "notification_month") val notification_month:String?,
    @ColumnInfo(name = "notify_before") val notify_before:Int?,
    @ColumnInfo(name = "repeat_want") val repeat_want: Int? = 0,
    @ColumnInfo(name = "repeat_count") val repeat_count: Int = 0,
)

data class ScheduleWithRelation(
    @Embedded
    val schedule: Schedule,
    @Relation(
        parentColumn = "account_id",
        entityColumn = "id"
    )
    val account: Account,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    )
    val category: Category
)
