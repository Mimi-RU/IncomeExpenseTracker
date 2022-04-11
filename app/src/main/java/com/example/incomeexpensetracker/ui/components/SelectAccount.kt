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
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.data.model.Account
import com.example.incomeexpensetracker.nav_routes

@Composable
fun selectAccount(
    account: Account?,
    onAccountChange: (Account) -> Unit,
    accountList: List<Account>,
    navHostController: NavHostController
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

        Text(text = account?.name ?: "Select Account")

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {

            accountList.onEach { account ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onAccountChange(account)
                    }
                ) {

                    Text(text = account.name)

                }
            }

            DropdownMenuItem(
                onClick = {
                    navHostController.navigate(nav_routes.account_add)
                }
            ) {

                Text(text = "Add New Account")

            }
        }
    }
}