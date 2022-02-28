package com.example.incomeexpensetracker.data.source.local
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.incomeexpensetracker.data.model.Note
@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(Note: Note)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getNotes(): LiveData<List<Note>>

    @Update
    suspend fun updateNote(Note: Note)

    @Delete
    suspend fun deleteNote(Note: Note)
}