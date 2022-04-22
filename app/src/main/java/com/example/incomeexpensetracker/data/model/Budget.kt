package com.example.incomeexpensetracker.data.model

import androidx.room.*

@Entity(
    tableName = "budgets",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("category_id"),
            onDelete = ForeignKey.CASCADE,
        )
    ]
)
data class Budget(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "category_id") val category_id: Int,
    @ColumnInfo(name = "period") val period: String,
    @ColumnInfo(name = "amount") val amount: Double,
)

data class BudgetWithRelation(
    @Embedded
    val budget: Budget,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    )
    val category: Category,
)
