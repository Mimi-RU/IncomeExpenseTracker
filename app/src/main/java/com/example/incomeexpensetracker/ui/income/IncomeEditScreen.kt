package com.example.incomeexpensetracker.ui.income

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

@Composable
fun incomeEditScreen(
    navHostController: NavHostController,
    id: Int
) {

    val incomeViewModel: IncomeViewModel = hiltViewModel()
    val accountViewModel: AccountViewModel = hiltViewModel()
    val categoryViewModel: CategoryViewModel = hiltViewModel()

    LaunchedEffect(key1 = id) {
        incomeViewModel.getIncomeByIdWithRelation(id)
    }

    val incomeToEdit = incomeViewModel.selectedIncomeWithRelation.value

    incomeViewModel.updateIncomeFields(incomeToEdit)

    val category by incomeViewModel.category
    val account by incomeViewModel.account
    val amount by incomeViewModel.amount


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

    Scaffold(
        topBar = {
            incomeEditTopBar(
                navHostController,
                incomeViewModel
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
fun incomeEditTopBar(navHostController: NavHostController, incomeViewModel: IncomeViewModel) {
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
            Text(text = "Edit Income")
        },
        actions = {

            IconButton(onClick = {
                incomeViewModel.updateIncome()
                navHostController.navigate(nav_routes.income_list)
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save Income")
            }

            IconButton(onClick = {
                incomeViewModel.deleteIncome()
                navHostController.navigate(nav_routes.income_list)
            }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Income")

            }

        }

    )
}
