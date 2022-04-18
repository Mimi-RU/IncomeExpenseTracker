package com.example.incomeexpensetracker.ui.expense

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
fun expenseEditScreen(navHostController: NavHostController, id: Int) {

    val expenseViewModel: ExpenseViewModel = hiltViewModel()
    val accountViewModel: AccountViewModel = hiltViewModel()
    val categoryViewModel: CategoryViewModel = hiltViewModel()

    LaunchedEffect(key1 = id) {
        expenseViewModel.getExpenseWithRelationById(id)
    }

    val expenseToEdit = expenseViewModel.selectedExpenseWithRelation.value

    if (expenseToEdit != null) {
        expenseViewModel.updateExpenseFields(expenseToEdit)
    }

    val category by expenseViewModel.category
    val account by expenseViewModel.account
    val amount by expenseViewModel.amount


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
    val categoryList = categoryStateList.value.filter { it.type == "Expense" }

    Scaffold(
        topBar = {
            expenseEditTopBar(
                navHostController,
                expenseViewModel
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
                    expenseViewModel.account.value = it
                },
                accountList = accountList,
                navHostController = navHostController
            )

            Text(text = "Category")
            selectCategory(
                category = category,
                onCategoryChange = {
                    expenseViewModel.category.value = it
                },
                categoryList = categoryList,
                navHostController = navHostController
            )

            enterAmount(
                amount = amount,
                onAmountChange = {
                    expenseViewModel.amount.value = it
                },
                label = "Enter Amount"
            )
        }
    }
}

@Composable
fun expenseEditTopBar(navHostController: NavHostController, expenseViewModel: ExpenseViewModel) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navHostController.navigate(nav_routes.expense_list) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to Expense List"
                )
            }
        },
        title = {
            Text(text = "Edit Expense")
        },
        actions = {

            IconButton(onClick = {
                expenseViewModel.updateExpense()
                navHostController.navigate(nav_routes.expense_list)
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save Expense")
            }

            IconButton(onClick = {
                expenseViewModel.deleteExpense()
                navHostController.navigate(nav_routes.expense_list)
            }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Expense")

            }

        }

    )

}