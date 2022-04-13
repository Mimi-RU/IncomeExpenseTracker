package com.example.incomeexpensetracker.ui.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.nav_routes
import com.example.incomeexpensetracker.ui.components.enterAmount
import com.example.incomeexpensetracker.ui.components.enterText
import com.example.incomeexpensetracker.ui.components.selectItem

@Composable
fun accountAddScreen(navHostController: NavHostController) {

    val accountViewModel: AccountViewModel = hiltViewModel()

    val name: String by accountViewModel.name
    val balance: String by accountViewModel.balance
    val accountType by accountViewModel.type
    val listOfAccountType = listOf<String>("Cash", "Bank", "Card")

    Scaffold(
        topBar = {
            accountAddTopBar(
                navHostController = navHostController,
                accountViewModel = accountViewModel
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            // Account Type
            Text(text = "Account Type")
            selectItem(
                item = accountType,
                onItemChange = {
                    accountViewModel.type.value = it
                },
                itemList = listOfAccountType
            )

            // Account Name
            enterText(
                label = "Account Name",
                value = name,
                onValueChange = {
                    accountViewModel.name.value = it
                }
            )

            // Initial Balance
            enterAmount(
                amount = balance,
                onAmountChange = {
                    accountViewModel.balance.value = it
                },
                label = "Initial Balance"
            )
        }
    }
}

@Composable
fun accountAddTopBar(navHostController: NavHostController, accountViewModel: AccountViewModel) {
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
                val pr = navHostController.previousBackStackEntry?.destination?.route
                //navHostController.navigate(nav_routes.account_list)
                navHostController.navigate(pr ?: nav_routes.account_list)
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Add Account")
            }

        }

    )
}