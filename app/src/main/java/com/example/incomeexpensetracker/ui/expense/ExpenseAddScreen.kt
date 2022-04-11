package com.example.incomeexpensetracker.ui.expense

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.data.model.Account
import com.example.incomeexpensetracker.data.model.Category
import com.example.incomeexpensetracker.nav_routes
import com.example.incomeexpensetracker.ui.account.AccountViewModel
import com.example.incomeexpensetracker.ui.category.CategoryViewModel
import com.example.incomeexpensetracker.ui.components.enterAmount
import com.example.incomeexpensetracker.ui.components.selectAccount

@Composable
fun expenseAddScreen(navHostController: NavHostController) {

    val expenseViewModel: ExpenseViewModel = hiltViewModel()
    val accountViewModel: AccountViewModel = hiltViewModel()
    val categoryViewModel: CategoryViewModel = hiltViewModel()

    // << getting account list
    LaunchedEffect(key1 = true) {
        accountViewModel.getAllAccounts()
    }
    val accountListState = accountViewModel.allAccounts.collectAsState()
    val accountList: List<Account> = accountListState.value
    // getting account list >>

    LaunchedEffect(key1 = true) {
        categoryViewModel.getAllCategories()
    }
    val categoryListState = categoryViewModel.allCategories.collectAsState()
    val categoryList: List<Category> = categoryListState.value

    val account by expenseViewModel.account
    val category by expenseViewModel.category
    val amount by expenseViewModel.amount

    Scaffold(
        topBar = {
            expenseAddTopBar(
                navHostController = navHostController,
                expenseViewModel = expenseViewModel
            )
        },
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            selectAccount(
                account = account,
                onAccountChange = {
                    expenseViewModel.account.value = it
                },
                accountList = accountList,
                navHostController = navHostController
            )
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
                }
            )
        }
    }
}


@Composable
fun selectCategory(
    category: Category?,
    onCategoryChange: (Category) -> Unit,
    categoryList: List<Category>,
    navHostController: NavHostController
) {

    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { expanded = true }
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurface
            )
    ) {

        Text(text = category?.name ?: "Select Category")

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {

            categoryList.onEach { category ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onCategoryChange(category)
                    }
                ) {

                    Text(text = category.name)

                }
            }

            DropdownMenuItem(
                onClick = {
                    navHostController.navigate(nav_routes.category_add)
                }
            ) {

                Text(text = "Add New Category")

            }
        }
    }
}

@Composable
fun expenseAddTopBar(navHostController: NavHostController, expenseViewModel: ExpenseViewModel) {
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
            Text(text = "Add Expense")
        },
        actions = {

            IconButton(onClick = {
                expenseViewModel.storeExpense()
                navHostController.navigate(nav_routes.expense_list)
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save Expense")
            }

        }
    )
}
