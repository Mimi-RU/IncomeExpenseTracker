package com.example.incomeexpensetracker.ui.budget

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
import com.example.incomeexpensetracker.data.model.Category
import com.example.incomeexpensetracker.nav_routes
import com.example.incomeexpensetracker.ui.category.CategoryViewModel
import com.example.incomeexpensetracker.ui.components.enterAmount
import com.example.incomeexpensetracker.ui.components.selectCategory
import com.example.incomeexpensetracker.ui.components.selectItem

@Composable
fun budgetAddScreen(navHostController: NavHostController) {

    val budgetViewModel: BudgetViewModel = hiltViewModel()
    val categoryViewModel: CategoryViewModel = hiltViewModel()

    LaunchedEffect(key1 = true) {
        categoryViewModel.getAllCategories()
    }
    val categoryListState = categoryViewModel.allCategories.collectAsState()
    val categoryList: List<Category> = categoryListState.value.filter { it.type == "Expense" }

    val category by budgetViewModel.category
    val period by budgetViewModel.period
    val amount by budgetViewModel.amount

    Scaffold(
        topBar = {
            budgetAddTopBar(
                navHostController = navHostController,
                budgetViewModel = budgetViewModel
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Text(text = "Category")
            selectCategory(
                category = category,
                onCategoryChange = {
                    budgetViewModel.category.value = it
                },
                categoryList = categoryList,
                navHostController = navHostController
            )

            // type
            val types = listOf<String>("Monthly", "Yearly")
            Text(text = "Period")
            selectItem(
                item = period,
                onItemChange = {
                    budgetViewModel.period.value = it
                },
                itemList = types
            )

            enterAmount(
                amount = amount,
                onAmountChange = {
                    budgetViewModel.amount.value = it
                },
                label = "Enter Amount"
            )
        }
    }
}

@Composable
fun budgetAddTopBar(navHostController: NavHostController, budgetViewModel: BudgetViewModel) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navHostController.navigate(nav_routes.budget_list) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to Budget List"
                )
            }
        },
        title = {
            Text(text = "Add Budget")
        },
        actions = {

            IconButton(onClick = {
                budgetViewModel.storeBudget()
                navHostController.navigate(nav_routes.budget_list)
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save Budget")
            }

        }
    )
}


