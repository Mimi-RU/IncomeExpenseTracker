package com.example.incomeexpensetracker.data.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "incomes",
        foreignKeys = [
        androidx.room.ForeignKey(
            entity = Tag::class,
            parentColumns = kotlin.arrayOf("id"),
            childColumns = kotlin.arrayOf(
                "tag_id",
            ),
            onDelete = CASCADE,
        ),
        androidx.room.ForeignKey(
            entity = Account::class,
            parentColumns = kotlin.arrayOf("id"),
            childColumns = kotlin.arrayOf("account_id"),
            onDelete = CASCADE
        )
])
data class Income(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "tag_id") val tag_id: Int,
    @ColumnInfo(name = "account_id") val account_id: Int,
    @ColumnInfo(name = "amount") val amount: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "month") val month:String,
    @ColumnInfo(name = "year") val year:String
)
