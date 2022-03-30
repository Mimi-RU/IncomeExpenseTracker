package com.example.incomeexpensetracker.ui.account

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.nav_routes

@Composable
fun accountAddScreen(navHostController: NavHostController, accountViewModel: AccountViewModel) {

    Scaffold(
        topBar = { accountAddTopBar(navHostController = navHostController, accountViewModel = accountViewModel) }
    ) {

        val name: String by accountViewModel.name
        val balance: String by accountViewModel.balance

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            accountTypeDropDown(accountViewModel = accountViewModel)

            OutlinedTextField(
                value = name,
                onValueChange = { accountViewModel.name.value = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Account Name") }
            )
            OutlinedTextField(
                value = balance,
                onValueChange = { accountViewModel.balance.value = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Initial Balance") }
            )
        }
    }
}


@Composable
fun accountTypeDropDown(
    accountViewModel: AccountViewModel
) {
    var expanded by remember { mutableStateOf(false) }

    var selectedIndex by remember { mutableStateOf(0) }

    val items = listOf("Cash", "Bank", "Card")

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

        Text(text = items[selectedIndex])

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {

            items.forEachIndexed { index, s ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        selectedIndex = index
                        accountViewModel.type.value = s
                    }
                ) {

                    Text(text = items[index])

                }
            }
        }
    }
}


@Composable
fun accountAddTopBar(navHostController: NavHostController, accountViewModel: AccountViewModel ) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navHostController.navigate(nav_routes.account_list) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to Account List"
                )
            }
        },
        title = {
            Text(text = "Add Account")
        },
        actions = {

            IconButton(onClick = {
                accountViewModel.storeAccount()
                navHostController.navigate(nav_routes.account_list)
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Add Account")

            }

        }

    )
}