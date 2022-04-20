package com.example.incomeexpensetracker.data.model

import androidx.room.*

@Entity(
    tableName = "pending_transactions",
    foreignKeys = [
        ForeignKey(
            entity = Schedule::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("schedule_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PendingTransaction(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "schedule_id") val schedule_id: Int,
    @ColumnInfo(name = "notified") val notified: Int = 0,
)

data class PendingTransactionWithRelation(
    @Embedded
    val pendingTransaction: PendingTransaction,
    @Relation(
        parentColumn = "schedule_id",
        entityColumn = "id"
    )
    val schedule: Schedule
)

