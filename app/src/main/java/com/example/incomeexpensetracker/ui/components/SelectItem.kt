package com.example.incomeexpensetracker.ui.components


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun selectItem(
    item: String?,
    onItemChange: (String) -> Unit,
    itemList: List<String>
) {

    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { expanded = true }
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurface
            )
    ) {

        item?.let { Text(text = it) }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {

            itemList.onEach { item ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onItemChange(item)
                    }
                ) {

                    Text(text = item)

                }
            }
        }
    }
}
