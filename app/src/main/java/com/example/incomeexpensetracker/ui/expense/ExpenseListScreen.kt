package com.example.incomeexpensetracker.ui.expense

import androidx.compose.foundation.border
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.data.model.ExpenseWithRelation
import com.example.incomeexpensetracker.data.model.IncomeWithRelations
import com.example.incomeexpensetracker.nav_routes


@Composable
fun expenseListScreen(navHostController: NavHostController) {

    val expenseViewModel: ExpenseViewModel = hiltViewModel()

    LaunchedEffect(key1 = true) {
        expenseViewModel.getAllExpenses()
    }
    val expenseListState = expenseViewModel.flowOfExpenses.collectAsState()
    val expenseList = expenseListState.value

    Scaffold(
        topBar = { expenseListTopBar(navHostController = navHostController) },
        floatingActionButton = {
            expenseListFloatingActionButton(navHostController = navHostController)
        }
    ) {

        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            items(expenseList) { item: ExpenseWithRelation ->
                expenseItemContent(expense = item, navHostController = navHostController)
            }
        }

    }

}
@Composable
fun expenseItemContent(expense: ExpenseWithRelation, navHostController: NavHostController) {

    Row(
        modifier = Modifier.clickable {
            navHostController.navigate("expense_edit/${expense.expense.id}")
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
                    text = "Category: " + expense.category.name,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(4.dp)
                )

                Text(
                    text = "Amount : " + expense.expense.amount,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(4.dp)
                )

                Text(
                    text = "Account : " + expense.account.name,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(4.dp)
                )

                Text(
                    text = "Date: " + expense.expense.date,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun expenseItem(expense: ExpenseWithRelation) {

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = expense.category.name,
            modifier = Modifier
                .weight(0.3f)
                .border(0.2.dp, Color.Black)
                .padding(4.dp),
            textAlign = TextAlign.Center

        )
        Text(
            text = expense.account.name,
            modifier = Modifier
                .weight(0.3f)
                .border(0.2.dp, Color.Black)
                .padding(4.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = expense.expense.amount,
            modifier = Modifier
                .weight(0.3f)
                .border(0.2.dp, Color.Black)
                .padding(4.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun expenseListFloatingActionButton(navHostController: NavHostController) {
    FloatingActionButton(onClick = { navHostController.navigate(nav_routes.expense_add) }) {

        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Expense"
        )
    }

}

@Composable
fun expenseListTopBar(navHostController: NavHostController) {
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
            Text(text = "Expenses")
        }
    )
}
