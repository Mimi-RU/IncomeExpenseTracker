package com.example.incomeexpensetracker.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun selectIntItem(
    item: Int?,
    onItemChange: (Int) -> Unit,
    itemList: List<Int>
) {

    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .clickable { expanded = true }
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurface,
                shape = RoundedCornerShape(8.dp)
            )
    ) {

        item?.let {
            Text(
                text = it.toString(),
                modifier = Modifier
                    .padding(
                        start = 12.dp,
                        top = 5.dp
                    )
            )
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {

            itemList.onEach { item ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onItemChange(item)
                    }
                ) {

                    Text(text = item.toString())

                }
            }
        }
    }
}