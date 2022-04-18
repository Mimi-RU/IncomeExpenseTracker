package com.example.incomeexpensetracker.ui.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.nav_routes
import com.example.incomeexpensetracker.ui.components.enterText

@Composable
fun noteAddScreen(navHostController: NavHostController) {
    val noteViewModel: NoteViewModel = hiltViewModel()

    val note by noteViewModel.note

    Scaffold(
        topBar = {
            noteAddTopBar(
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
fun noteAddTopBar(navHostController: NavHostController, noteViewModel: NoteViewModel) {
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
            Text(text = "Add Note")
        },
        actions = {

            IconButton(onClick = {
                noteViewModel.storeNote()
                val pr = navHostController.previousBackStackEntry?.destination?.route
                navHostController.navigate(pr ?: nav_routes.note_list)
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save Note"
                )
            }

        }

    )
}
