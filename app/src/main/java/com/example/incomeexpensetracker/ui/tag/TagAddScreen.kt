package com.example.incomeexpensetracker.ui.tag

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

@Composable
fun tagAddScreen(navHostController: NavHostController){
    
    val tagViewModel: TagViewModel = hiltViewModel()

    val name by tagViewModel.name

    Scaffold(
        topBar = {
                tagAddTopBar(navHostController = navHostController, tagViewModel = tagViewModel ) 
        }
    ) {
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            tagAddFrom(
                name = name,
                onNameChange = {
                    tagViewModel.name.value = it
                }
            )
        }

    }
}

@Composable
fun tagAddFrom( name : String, onNameChange : (String) -> Unit) {
   OutlinedTextField(
       value = name,
       onValueChange = {onNameChange(it)},
       label = { Text(text = "Enter Name")},
       modifier = Modifier.fillMaxWidth()
   )
}

@Composable
fun tagAddTopBar(navHostController: NavHostController,tagViewModel: TagViewModel) {

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navHostController.navigate(nav_routes.tag_list) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to Tag List"
                )
            }
        },
        title = {
            Text(text = "Add Tag")
        },
        actions = {

            IconButton(onClick = {
                tagViewModel.storeTag()
                navHostController.navigate(nav_routes.income_add)
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save Tag")
            }

        }

    )
}
