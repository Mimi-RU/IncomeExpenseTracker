package com.example.incomeexpensetracker.ui.account


import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.data.model.Account
import com.example.incomeexpensetracker.nav_routes

@Composable
fun accountEditScreen(
    navHostController: NavHostController,
    accountViewModel: AccountViewModel,
    id: Int
) {

    Scaffold(
        topBar = {
            accountEditTopBar(
                navHostController = navHostController,
                accountViewModel = accountViewModel
            )
        }
    ) {

        accountViewModel.getAccountById(id)
        val account: Account? by accountViewModel.selectedAccount.collectAsState()
        LaunchedEffect(key1 = account?.id) {
            accountViewModel.updateAccountFields(account)
        }

        val name: String by accountViewModel.name
        val type: String by accountViewModel.type
        val balance: String by accountViewModel.balance

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            typeDropDown(accountViewModel = accountViewModel, type = type)

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
fun typeDropDown(
    accountViewModel: AccountViewModel,
    type: String
) {
    var expanded by remember { mutableStateOf(false) }

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

        Text(text = type)

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {

            items.forEachIndexed { index, s ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
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
fun accountEditTopBar(navHostController: NavHostController, accountViewModel: AccountViewModel) {
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
            Text(text = "Edit Account")
        },
        actions = {

            IconButton(onClick = {
                accountViewModel.deleteAccount()
                navHostController.navigate(nav_routes.account_list)
            }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Account")

            }

            IconButton(onClick = {
                accountViewModel.updateAccount()
                navHostController.navigate(nav_routes.account_list)
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save Account")

            }

        }

    )
}