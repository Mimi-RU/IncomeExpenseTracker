package com.example.incomeexpensetracker.ui.income

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.nav_routes
import com.example.incomeexpensetracker.ui.account.AccountViewModel
import com.example.incomeexpensetracker.ui.category.CategoryViewModel
import com.example.incomeexpensetracker.ui.components.enterAmount
import com.example.incomeexpensetracker.ui.components.selectAccount
import com.example.incomeexpensetracker.ui.components.selectCategory

@Composable
fun incomeAddScreen(navHostController: NavHostController) {

    val incomeViewModel: IncomeViewModel = hiltViewModel()
    val accountViewModel: AccountViewModel = hiltViewModel()
    val categoryViewModel : CategoryViewModel = hiltViewModel()

    // account list
    LaunchedEffect(key1 = true) {
        accountViewModel.getAllAccounts()
    }
    val accountStateList = accountViewModel.allAccounts.collectAsState()
    val accountList = accountStateList.value

    // category list
    LaunchedEffect(key1 = true) {
        categoryViewModel.getAllCategories()
    }
    val categoryStateList = categoryViewModel.allCategories.collectAsState()
    val categoryList = categoryStateList.value.filter { it.type == "Income" }

    val account by incomeViewModel.account
    val category by incomeViewModel.category
    val amount by incomeViewModel.amount


    Scaffold(
        topBar = {
            incomeAddTopBar(
                navHostController,
                incomeViewModel = incomeViewModel
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            
            Text(text = "Account")
            selectAccount(
                account = account,
                onAccountChange = {
                    incomeViewModel.account.value = it
                },
                accountList = accountList,
                navHostController = navHostController
            )
    
            Text(text = "Category")
            selectCategory(
                category = category,
                onCategoryChange = {
                    incomeViewModel.category.value = it
                },
                categoryList = categoryList,
                navHostController = navHostController
            )

            enterAmount(
                amount = amount,
                onAmountChange = {
                    incomeViewModel.amount.value = it
                },
                label = "Enter Amount"
            )
        }
    }
}

@Composable
fun incomeAddTopBar(navHostController: NavHostController, incomeViewModel: IncomeViewModel) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navHostController.navigate(nav_routes.income_list) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to Income List"
                )
            }
        },
        title = {
            Text(text = "Add Income")
        },
        actions = {

            IconButton(onClick = {
                incomeViewModel.storeIncome()
                navHostController.navigate(nav_routes.income_list)
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save Income")
            }

        }

    )
}
