package com.example.incomeexpensetracker.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun topBarScreen() {

    TopAppBar(
        title = {
            Text(
                text = "Income-Expense Tracker",
                textAlign = TextAlign.Center
            )
        },
//        navigationIcon = {
//            IconButton(onClick = {}) {
//                Icon(Icons.Filled.List, "ListIcon")
//            }
//        },
        backgroundColor = MaterialTheme.colors.primaryVariant,
        contentColor = Color.White,
        elevation = 10.dp
    )
}
