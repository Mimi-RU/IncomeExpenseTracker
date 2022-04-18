package com.example.incomeexpensetracker.ui.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
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
import com.example.incomeexpensetracker.ui.category.CategoryViewModel
import com.example.incomeexpensetracker.ui.components.enterAmount
import com.example.incomeexpensetracker.ui.components.selectAccount
import com.example.incomeexpensetracker.ui.components.selectCategory
import com.example.incomeexpensetracker.ui.components.selectItem

@Composable
fun scheduleEditScreen(navHostController: NavHostController, id:Int){

    val scheduleViewModel: ScheduleViewModel = hiltViewModel()
    val accountViewModel: AccountViewModel = hiltViewModel()
    val categoryViewModel: CategoryViewModel = hiltViewModel()

    // << set values with selected schedule
    LaunchedEffect(key1 = id ){
        scheduleViewModel.getScheduleWithRelationById(id = id)
    }


    val scheduleWithRelation = scheduleViewModel.selectedScheduleWithRelation.value
    scheduleViewModel.updateScheduleFields(scheduleWithRelation = scheduleWithRelation)

    // set values with selected schedule >>
    val scheduleFor by scheduleViewModel.type
    val account by scheduleViewModel.account
    val category by scheduleViewModel.category
    val amount by scheduleViewModel.amount
    val intervalUnit by scheduleViewModel.intervalUnit
    val types = listOf<String>("Income", "Expense")

   // < accounts
    LaunchedEffect(key1 = true) {
        accountViewModel.getAllAccounts()
    }
    val accountStateList = accountViewModel.allAccounts.collectAsState()
    val accountList = accountStateList.value
    // accounts >>

    //<category
    LaunchedEffect(key1 = true) {
        categoryViewModel.getAllCategories()
    }
    val categoryStateList = categoryViewModel.allCategories.collectAsState()
    val categoryList = categoryStateList.value.filter {
        it.type == scheduleFor
    }
    //category>


    Scaffold(
        topBar = { scheduleEditTopBar(navHostController, scheduleViewModel) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            // type
            Text(text = "Schedule For")
            selectItem(
                item = scheduleFor,
                onItemChange = {
                    scheduleViewModel.type.value = it
                },
                itemList = types
            )

            // account_id
            Text(text = "Account")
            selectAccount(
                account = account,
                onAccountChange = {
                    scheduleViewModel.account.value = it
                },
                accountList = accountList,
                navHostController = navHostController
            )

            // category_id
            Text(text = "Category")
            selectCategory(
                category = category,
                onCategoryChange = {
                    scheduleViewModel.category.value = it
                },
                categoryList = categoryList,
                navHostController = navHostController
            )

            // amount
            enterAmount(
                amount = amount,
                onAmountChange = {
                    scheduleViewModel.amount.value = it
                },
                label = "Enter Amount"
            )

            // interval_unit
            val intervals = listOf<String>("Daily", "Weekly", "Monthly", "Yearly")
            Text(text = "Repeats")
            selectItem(
                item = intervalUnit,
                onItemChange = {
                    scheduleViewModel.intervalUnit.value = it
                },
                itemList = intervals
            )
        }
    }
}

@Composable
fun scheduleEditTopBar(navHostController: NavHostController, scheduleViewModel: ScheduleViewModel) {
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
            Text(text = "Edit Schedule")
        },
        actions = {

            IconButton(onClick = {
                scheduleViewModel.updateSchedule()
                navHostController.navigate(nav_routes.schedule_list)
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save Schedule")
            }

            IconButton(onClick = {
                scheduleViewModel.deleteSchedule()
                navHostController.navigate(nav_routes.schedule_list)
            }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Schedule")

            }

        }

    )
}