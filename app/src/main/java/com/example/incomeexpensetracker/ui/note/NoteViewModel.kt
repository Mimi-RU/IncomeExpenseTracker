package com.example.incomeexpensetracker.ui.note


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.incomeexpensetracker.data.model.Account
import com.example.incomeexpensetracker.data.model.Note
import com.example.incomeexpensetracker.data.repository.AccountRepository
import com.example.incomeexpensetracker.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) :
    ViewModel() {

    val id: MutableState<Int> = mutableStateOf(0)
    val note: MutableState<String> = mutableStateOf("")
    val status: MutableState<Int> = mutableStateOf(0)
    val date: MutableState<String> = mutableStateOf("")

    // << All Notes
    private val _allNotes = MutableStateFlow<List<Note>>(emptyList())

    val allNotes = _allNotes

    fun getAllNotes() {
        viewModelScope.launch {
            noteRepository.allNote.collect {
                _allNotes.value = it
            }
        }
    }
    // All Notes >>

    //  << Get Note By Id
    private val _selectedNote = MutableStateFlow<Note?>(null)

    val selectedNote = _selectedNote

    fun getNoteById(id: Int) {
        viewModelScope.launch {
            noteRepository.getNoteById(id).collect { note ->
                selectedNote.value = note
            }
        }
    }

    fun updateNoteFields(selectedNote: Note?) {
        if (selectedNote != null) {
            id.value = selectedNote.id
            note.value = selectedNote.note
            status.value = selectedNote.status
            date.value = selectedNote.date
        } else {
            id.value = 0
            note.value = ""
            status.value = 0
            date.value = ""
        }
    }
    // Get Note By Id >>

    // << Insert
    private suspend fun insertNote() {
        viewModelScope.launch { Dispatchers.IO }
        val note = Note(
            id = 0,
            note = note.value,
            status = status.value,
            date = date.value
        )
        noteRepository.insert(note)
    }

    fun storeNote() = viewModelScope.launch {
        insertNote()
    }
    // Insert >>

    // << Update
    private suspend fun _updateNote() {
        viewModelScope.launch { Dispatchers.IO }
        val note = Note(
            id = id.value,
            note = note.value,
            status = status.value,
            date = date.value
        )
        noteRepository.update(note)
    }

    fun updateNote() = viewModelScope.launch {
        _updateNote()
    }
    // Update >>

    // << Delete
    private suspend fun _deleteNote() {
        viewModelScope.launch { Dispatchers.IO }
        val note = Note(
            id = id.value,
            note = note.value,
            status = status.value,
            date = date.value
        )
        noteRepository.delete(note)
    }

    fun deleteNote() = viewModelScope.launch {
        _deleteNote()
    }
    // Delete >>

}