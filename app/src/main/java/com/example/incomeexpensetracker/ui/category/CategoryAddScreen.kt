package com.example.incomeexpensetracker.ui.category

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
fun categoryAddScreen(navHostController: NavHostController) {

    val categoryViewModel: CategoryViewModel = hiltViewModel()

    val name by categoryViewModel.name

    Scaffold(
        topBar = {
            categoryAddTopBar(
                navHostController = navHostController,
                categoryViewModel = categoryViewModel
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            categoryAddFrom(
                name = name,
                onNameChange = {
                    categoryViewModel.name.value = it
                }
            )
        }

    }
}

@Composable
fun categoryAddFrom(name: String, onNameChange: (String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = { onNameChange(it) },
        label = { Text(text = "Enter Name") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun categoryAddTopBar(navHostController: NavHostController, categoryViewModel: CategoryViewModel) {


    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navHostController.navigate(nav_routes.category_list) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to Category List"
                )
            }
        },
        title = {
            Text(text = "Add Category")
        },
        actions = {

            IconButton(onClick = {
                categoryViewModel.storeCategory()
                navHostController.navigate(nav_routes.category_list)
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save Category"
                )
            }

        }

    )
}
