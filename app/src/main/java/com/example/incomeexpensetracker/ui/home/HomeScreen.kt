package com.example.incomeexpensetracker.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.data.model.IncomeWithRelations
import com.example.incomeexpensetracker.nav_routes
import com.example.incomeexpensetracker.ui.account.AccountViewModel
import com.example.incomeexpensetracker.ui.components.topBarScreen
import com.example.incomeexpensetracker.ui.expense.ExpenseViewModel
import com.example.incomeexpensetracker.ui.income.IncomeViewModel
import kotlinx.coroutines.flow.asStateFlow
import me.bytebeats.views.charts.pie.PieChart
import me.bytebeats.views.charts.pie.PieChartData
import me.bytebeats.views.charts.pie.render.SimpleSliceDrawer
import me.bytebeats.views.charts.simpleChartAnimation

@Composable
fun homeScreen(navHostController: NavHostController) {

    val homeViewModel: HomeViewModel = hiltViewModel()
    val expenseViewModel: ExpenseViewModel = hiltViewModel()
    val incomeViewModel: IncomeViewModel = hiltViewModel()
    val accountViewModel: AccountViewModel = hiltViewModel()

    val selectedTab by homeViewModel.selectedTab
    val thisDate by homeViewModel.thisDate
    val thisMonth by homeViewModel.thisMonth
    val thisYear by homeViewModel.thisYear

    val chartTitle = when (selectedTab) {
        "Daily" -> {
            "Date: $thisDate"
        }
        "Monthly" -> {
            "Month: $thisMonth, $thisYear"
        }
        else -> {
            "Year: $thisYear"
        }
    }

    // << calculate total Expense
    LaunchedEffect(key1 = (0..100).random()) {
        expenseViewModel.getAllExpenses()
    }
    val expenseListState = expenseViewModel.flowOfExpenses.collectAsState()
    val expenseList = expenseListState.value
    val totalExpense = when(selectedTab){
        "Daily" -> {
            expenseList
                .filter { it.expense.date == thisDate && it.expense.month == thisMonth && it.expense.year == thisYear }
                .map { it.expense.amount.toDouble() }
                .sumOf { it }
        }
        "Monthly" -> {
            expenseList
                .filter { it.expense.month == thisMonth && it.expense.year == thisYear }
                .map { it.expense.amount.toDouble() }
                .sumOf { it }
        }
        else -> {
            expenseList
                .filter { it.expense.year == thisYear }
                .map { it.expense.amount.toDouble() }
                .sumOf { it }
        }
    }
    // calculate total Expense >>

    // << Calculate Total Income
    LaunchedEffect(key1 = (0..100).random()) {
        incomeViewModel.getAllIncomesWithRelations()
    }
    val incomeFlow = incomeViewModel.allIncomesWithRelations.asStateFlow()
    val incomeList: List<IncomeWithRelations> = incomeFlow.value
    val totalIncome = when(selectedTab) {
        "Daily" -> {
            incomeList
                .filter { it.income.date == thisDate && it.income.month == thisMonth && it.income.year == thisYear }
                .map { it.income.amount.toDouble() }
                .sumOf { it }
        }
        "Monthly" -> {
            incomeList
                .filter { it.income.month == thisMonth && it.income.year == thisYear }
                .map { it.income.amount.toDouble() }
                .sumOf { it }
        }
        else -> {
            incomeList
                .filter { it.income.year == thisYear }
                .map { it.income.amount.toDouble() }
                .sumOf { it }
        }
    }
    // Calculate Total Income>>

    // << Calculate Account Balance
    LaunchedEffect(key1 = (0..100).random()) {
        accountViewModel.getAllAccounts()
    }
    val accountStateList = accountViewModel.allAccounts.collectAsState()
    val accountList = accountStateList.value
    val totalBalance = accountList.map { it.balance }.sumOf { it }
    // Calculate Account  Balance>>

    Scaffold(
        topBar = { topBarScreen() },
        floatingActionButton = {
            homeFloatingActionButton(navHostController = navHostController)
        }
    ) {

        Column {

            // tabs
            tabRowScreen {
                homeViewModel.selectedTab.value = it
            }

            // chart content
            Row {

                pieChartView(
                    totalExpense = totalExpense,
                    totalIncome = totalIncome,
                    totalBalance = totalBalance
                )

                Column(
                    modifier = Modifier.padding(10.dp)
                ) {

                    Text(
                        text = "Total Expense : $totalExpense TK",
                        color = Color(0xFFC21336.toInt()),
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "Total Income : $totalIncome TK",
                        color = Color(0xFF347539.toInt()),
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "Total Balance : $totalBalance TK",
                        color = Color(0xFF415575.toInt()),
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = chartTitle,
                        color = Color(0xFF415575.toInt()),
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }

            // Incomes
            Row {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = Color(0xFFA3AB78.toInt()),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            navHostController.navigate(nav_routes.income_list)
                        }
                ) {
                    Text(
                        text = "Incomes",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            // Expenses
            Row {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = Color(0xFF506266.toInt()),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            navHostController.navigate(nav_routes.expense_list)
                        }

                ) {
                    Text(
                        text = "Expenses",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            // Accounts
            Row {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = Color(0xFF818274.toInt()),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            navHostController.navigate(nav_routes.account_list)
                        }
                ) {
                    Text(
                        text = "Accounts",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            // Schedules
            Row {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = Color(0xFF8C7558.toInt()),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            navHostController.navigate(nav_routes.schedule_list)
                        }
                ) {
                    Text(
                        text = "Schedules",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            // Budgets
            Row {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = Color(0xFF589A8D.toInt()),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            navHostController.navigate(nav_routes.budget_list)
                        }
                ) {
                    Text(
                        text = "Budgets",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            // Notes
            Row {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = Color(0xFFD6D58E.toInt()),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            navHostController.navigate(nav_routes.note_list)
                        }
                ) {
                    Text(
                        text = "Notes",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun pieChartView(totalExpense: Double, totalIncome: Double, totalBalance: Double) {
    PieChart(
        pieChartData = PieChartData(
            slices = listOf(
                PieChartData.Slice(
                    totalExpense.toFloat(),
                    Color(0xFFC21336.toInt())
                ),
                PieChartData.Slice(
                    totalIncome.toFloat(),
                    Color(0xFF347539.toInt())
                ),
                PieChartData.Slice(
                    totalBalance.toFloat(),
                    Color(0xFF415575.toInt())
                )
            )
        ),
        // Optional properties.
        modifier = Modifier
            .padding(10.dp)
            .width(120.dp)
            .height(120.dp),
        animation = simpleChartAnimation(),
        sliceDrawer = SimpleSliceDrawer()
    )
}


@Composable
fun homeFloatingActionButton(navHostController: NavHostController) {
    FloatingActionButton(
        onClick = { navHostController.navigate(nav_routes.expense_add) }
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Expense"
        )
    }
}