package com.example.incomeexpensetracker.ui.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.nav_routes
import com.example.incomeexpensetracker.ui.components.enterText

@Composable
fun noteEditScreen(
    navHostController: NavHostController,
    id: Int){

    val noteViewModel: NoteViewModel = hiltViewModel()

    LaunchedEffect(key1 = id){
        noteViewModel.getNoteById(id)
    }

    val selectedNote = noteViewModel.selectedNote.value
    noteViewModel.updateNoteFields(selectedNote = selectedNote)

    val note by noteViewModel.note

    Scaffold(
        topBar = {
            noteEditTopBar(
                navHostController = navHostController,
                noteViewModel = noteViewModel
            )
        }
    )
    {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {


            // name
            enterText(
                label = "Note",
                value = note,
                onValueChange = {
                    noteViewModel.note.value = it
                }
            )
        }

    }


}
@Composable
fun noteEditTopBar(navHostController: NavHostController, noteViewModel: NoteViewModel) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navHostController.navigate(nav_routes.note_list) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to Note List"
                )
            }
        },
        title = {
            Text(text = "Edit Note")
        },
        actions = {

            IconButton(onClick = {
                noteViewModel.updateNote()
                navHostController.navigate(nav_routes.note_list)
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save Note")
            }

            IconButton(onClick = {
                noteViewModel.deleteNote()
                navHostController.navigate(nav_routes.note_list)
            }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Note")

            }

        }

    )
}



