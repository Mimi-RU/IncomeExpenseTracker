package com.example.incomeexpensetracker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "expenses",
    foreignKeys = [
        ForeignKey(
            entity = Category::class, parentColumns = arrayOf("id"),
            childColumns = arrayOf(
                "category_id",
            ),
            onDelete = CASCADE,
        ),
        ForeignKey(
            entity = Account::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("account_id"),
            onDelete = CASCADE
        )
    ]
)
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "category_id") val category_id: Int,
    @ColumnInfo(name = "account_id") val account_id: Int,
    @ColumnInfo(name = "amount") val amount: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "month") val month: String,
    @ColumnInfo(name = "year") val year: String
)
