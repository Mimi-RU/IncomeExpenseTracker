package com.example.incomeexpensetracker.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun enterAmount(
    amount: String,
    onAmountChange: (String) -> Unit
) {
    OutlinedTextField(
        value = amount,
        onValueChange = { onAmountChange(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Enter Amount") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )
}