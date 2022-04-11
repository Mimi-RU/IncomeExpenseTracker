package com.example.incomeexpensetracker.ui.account

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.data.model.Account
import com.example.incomeexpensetracker.nav_routes

@Composable
fun accountListScreen(navHostController: NavHostController) {

    val accountViewModel: AccountViewModel = hiltViewModel()

    val scaffoldState: ScaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        accountViewModel.getAllAccounts()
    }

    val accountStateList = accountViewModel.allAccounts.collectAsState()

    val accountList = accountStateList.value

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { accountsTopBar(navHostController) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navHostController.navigate(nav_routes.account_add)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Accounts"
                )

            }
        }
    ) {

        LazyColumn(
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(accountList) { item: Account ->
                accountItem(account = item, navHostController = navHostController)
            }
        }
    }

}


@Composable
fun accountsTopBar(navHostController: NavHostController) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navHostController.navigate(nav_routes.home) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to Home"
                )
            }
        },
        title = {
            Text(text = "Accounts")
        }
    )
}


@Composable
fun accountItem(account: Account, navHostController: NavHostController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navHostController.navigate("account_edit/${account.id}") }
    ) {
        Text(
            text = account.name,
            modifier = Modifier
                .weight(0.3f)
                .border(0.2.dp, Color.Black)
                .padding(4.dp),
            textAlign = TextAlign.Center

        )
        Text(
            text = account.balance,
            modifier = Modifier
                .weight(0.3f)
                .border(0.2.dp, Color.Black)
                .padding(4.dp),
            textAlign = TextAlign.Center
        )
    }
}
