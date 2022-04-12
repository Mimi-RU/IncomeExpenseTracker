package com.example.incomeexpensetracker.ui.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.nav_routes
import com.example.incomeexpensetracker.ui.account.AccountViewModel
import com.example.incomeexpensetracker.ui.components.selectAccount

@Composable
fun scheduleAddScreen(navHostController: NavHostController) {

    val scheduleViewModel: ScheduleViewModel = hiltViewModel()
    val accountViewModel: AccountViewModel = hiltViewModel()

    LaunchedEffect(key1 = true ){
        accountViewModel.getAllAccounts()
    }
    val accountStateList = accountViewModel.allAccounts.collectAsState()
    val accountList = accountStateList.value

    val type by scheduleViewModel.type
    val account by scheduleViewModel.account

    Scaffold(
        topBar = { scheduleAddTopBar(navHostController, scheduleViewModel) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            // type
            selectType(
                type = type,
                onTypeChange = {
                    scheduleViewModel.type.value = it
                }
            )

            // account_id
            selectAccount(
                account = account,
                onAccountChange = {
                    scheduleViewModel.account.value = it
                },
                accountList = accountList,
                navHostController = navHostController
            )

            // amount

        }
    }
}

@Composable
fun selectType(type: String, onTypeChange: (String) -> Unit) {

}

@Composable
fun scheduleAddTopBar(navHostController: NavHostController, scheduleViewModel: ScheduleViewModel) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navHostController.navigate(nav_routes.schedule_list) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to Schedule List"
                )
            }
        },
        title = {
            Text(text = "Add Schedule")
        },
        actions = {

            IconButton(onClick = {
                scheduleViewModel.storeSchedule()
                navHostController.navigate(nav_routes.schedule_list)
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save Schedule")
            }

        }

    )
}
