package com.example.incomeexpensetracker.ui.budget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.data.model.BudgetWithRelation
import com.example.incomeexpensetracker.nav_routes


@Composable
fun budgetListScreen(navHostController: NavHostController) {
    val budgetViewModel: BudgetViewModel = hiltViewModel()

    LaunchedEffect(key1 = true) {
        budgetViewModel.getALlBudgets()
    }

    val budgetListState = budgetViewModel.flowOfBudgets.collectAsState()
    val budgetList = budgetListState.value

    Scaffold(
        topBar = {
            budgetListTopBar(navHostController = navHostController)
        },
        floatingActionButton = {
            budgetListFloatingActionButton(navHostController = navHostController)
        }
    ) {

        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            items(budgetList) { item: BudgetWithRelation ->
                budgetItemContent(budget = item, navHostController = navHostController)
            }
        }

    }

}

@Composable
fun budgetItemContent(budget: BudgetWithRelation, navHostController: NavHostController) {

    Row(
        modifier = Modifier.clickable {
            navHostController.navigate("budget_edit/${budget.budget.id}")
        }
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color(0xFFD9D9D9.toInt()),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Column {

                Text(
                    text = "Category: " + budget.category.name,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(4.dp)
                )

                Text(
                    text = "Period : " + budget.budget.period,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(4.dp)
                )

                Text(
                    text = "Amount : " + budget.budget.amount + " TK",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun budgetListFloatingActionButton(navHostController: NavHostController) {

    FloatingActionButton(onClick = { navHostController.navigate(nav_routes.budget_add) }) {

        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Budget"
        )
    }

}

@Composable
fun budgetListTopBar(navHostController: NavHostController) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navHostController.navigate(nav_routes.home) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to Home"
                )
            }
        },
        title = {
            Text(text = "Budgets")
        }
    )
}
