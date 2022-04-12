package com.example.incomeexpensetracker.data.model

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(
    tableName = "incomes",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("category_id"),
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

data class Income(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "category_id") val category_id: Int,
    @ColumnInfo(name = "account_id") val account_id: Int,
    @ColumnInfo(name = "amount") val amount: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "month") val month: String,
    @ColumnInfo(name = "year") val year: String
)

data class IncomeWithRelations(
    @Embedded val income: Income,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    )
    val category: Category,
    @Relation(
        parentColumn = "account_id",
        entityColumn = "id"
    )
    val account: Account
)

