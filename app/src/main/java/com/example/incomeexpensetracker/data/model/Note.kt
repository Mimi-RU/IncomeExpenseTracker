package com.example.incomeexpensetracker.data.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "note") val note:String,
    @ColumnInfo(name = "status") val status:Int,
    @ColumnInfo(name = "icon") val icon: String? = null,
    @ColumnInfo(name = "date") val date: String,
)
