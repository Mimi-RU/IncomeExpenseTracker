package com.example.incomeexpensetracker.data.repository

import com.example.incomeexpensetracker.data.model.Note
import com.example.incomeexpensetracker.data.source.local.NoteDao
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    val allNote: Flow<List<Note>> = noteDao.getNotes()

    suspend fun getNoteById(id: Int) : Flow<Note> {
        return noteDao.getNoteById(id)
    }

    suspend fun insert(note: Note) {
        noteDao.insertNote(note)
    }

    suspend fun update(note: Note) {
        noteDao.updateNote(note)
    }

    suspend fun delete(note: Note) {
        noteDao.deleteNote(note)
    }
}