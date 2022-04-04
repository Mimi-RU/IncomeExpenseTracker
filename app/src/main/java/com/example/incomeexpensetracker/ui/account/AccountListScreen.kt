package com.example.incomeexpensetracker.ui.account

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.data.model.Account
import com.example.incomeexpensetracker.nav_arguments
import com.example.incomeexpensetracker.nav_routes
import com.example.incomeexpensetracker.ui.components.bottomBar
import com.example.incomeexpensetracker.ui.components.topBarScreen

@Composable
fun accountListScreen(navHostController: NavHostController, accountViewModel : AccountViewModel) {

    val scaffoldState: ScaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true){
        accountViewModel.getAllAccounts()
    }

    val accountStateList = accountViewModel.allAccounts.collectAsState()

    val  accountList = accountStateList.value

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { accountsTopBar() },
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
            items(accountList){ item: Account ->
                accountItem(account = item, navHostController = navHostController)
            }
        }
    }

}

@Composable
fun accountsTopBar(){
    TopAppBar() {
        Text(text = "Accounts")

    }
}

@Composable
fun accountItem(account : Account, navHostController: NavHostController) {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.background,
        shape = RectangleShape,
        elevation = 2.dp
    ){

        Column(
            modifier = Modifier
                .padding(12.dp)
                .clickable { navHostController.navigate("account_edit/${account.id}") }
        ) {

                Text(text = account.name)
                Text(text = account.balance)

        }
    }

}
