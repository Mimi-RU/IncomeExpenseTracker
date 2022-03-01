package com.example.incomeexpensetracker.ui.account

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.incomeexpensetracker.ui.components.bottomBar
import com.example.incomeexpensetracker.ui.components.topBarScreen
import com.example.incomeexpensetracker.ui.home.homeFloatingActionButton

@Composable
fun accountListScreen(accountViewModel: AccountViewModel) {

    Scaffold(
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
