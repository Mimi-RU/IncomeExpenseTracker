package com.example.incomeexpensetracker.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun topBarScreen(){
    
    TopAppBar (
        title = {
            Text(text = "Expense Tracker")
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.List, "ListIcon")
            }
        },
        backgroundColor = MaterialTheme.colors.primaryVariant,
        contentColor = Color.White,
        elevation = 10.dp
        )
}