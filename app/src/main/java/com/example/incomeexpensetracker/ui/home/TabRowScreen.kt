package com.example.incomeexpensetracker.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable
fun tabRowScreen(onTabSelect: (String) -> Unit) {

    var state by remember { mutableStateOf(0) }

    val titles = listOf("Daily", "Monthly", "Yearly")

    Column {
        TabRow(
            selectedTabIndex = state,
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = (index == state),
                    onClick = {
                        state = index
                        onTabSelect(title)
                    },
                    text = { Text(text = title) },
                )
            }
        }
    }
}