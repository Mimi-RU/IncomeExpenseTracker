package com.example.incomeexpensetracker.ui.account

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import com.example.incomeexpensetracker.ui.components.bottomBar
import com.example.incomeexpensetracker.ui.components.topBarScreen
import com.example.incomeexpensetracker.ui.home.homeFloatingActionButton

@Composable
fun accountListScreen() {

    val accountViewModel = AccountViewModel()

    val scaffoldState: ScaffoldState = rememberScaffoldState()

    
    // Text(text = "Account List")
    
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { topBarScreen()},
        bottomBar = { bottomBar() }
    ) {

        accountList(accountViewModel = accountViewModel)

    }

}

@Composable
fun accountList(accountViewModel: AccountViewModel) {

    val list = accountViewModel.allAccount

    list.value?.forEach() {
        Column() {
            Text(text = "$it.name")
        }
    }
}

