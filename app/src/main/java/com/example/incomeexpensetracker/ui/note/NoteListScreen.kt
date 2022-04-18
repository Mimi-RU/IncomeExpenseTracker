package com.example.incomeexpensetracker.ui.note

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.data.model.Note
import com.example.incomeexpensetracker.nav_routes
import com.example.incomeexpensetracker.ui.components.backTOHomeTopAppBar

@Composable
fun noteListScreen(navHostController: NavHostController) {
    val noteViewModel: NoteViewModel = hiltViewModel()

    LaunchedEffect(key1 = true) {
        noteViewModel.getAllNotes()
    }
    val noteListState = noteViewModel.allNotes.collectAsState()
    val noteList = noteListState.value

    Scaffold(
        topBar = {
            backTOHomeTopAppBar(
                title = "Notes",
                navHostController = navHostController
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navHostController.navigate(nav_routes.note_add)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Note"
                )
            }
        }
    )
    {

        LazyColumn(
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(noteList) { item: Note ->
                noteItem(note = item, navHostController = navHostController)
            }
        }

    }
}

@Composable
fun noteItem(note: Note, navHostController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { navHostController.navigate("note_edit/${note.id}") }
    ) {
        Text(
            text = note.note,
            modifier = Modifier
                .weight(0.5f),
            textAlign = TextAlign.Center
        )

    }

    Divider()

}


