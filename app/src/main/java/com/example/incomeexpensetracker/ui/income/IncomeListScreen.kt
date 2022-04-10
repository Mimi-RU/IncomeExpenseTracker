package com.example.incomeexpensetracker.ui.income

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.nav_routes

@Composable
fun incomeListScreen(navHostController: NavHostController) {

    Scaffold(
        topBar = { incomeListtopBar(navHostController) },

        floatingActionButton = { incomeListfloatingActionButton(navHostController) }
    ) {


    }

}

@Composable
fun incomeListfloatingActionButton(navHostController: NavHostController) {

    FloatingActionButton(onClick = { navHostController.navigate(nav_routes.income_add) }) {

        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Income"
        )
    }

}

@Composable
fun incomeListtopBar(navHostController: NavHostController) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navHostController.navigate(nav_routes.home) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to Home"
                )
            }
        },
        title = {
            Text(text = "Incomes")
        }
    )
}

