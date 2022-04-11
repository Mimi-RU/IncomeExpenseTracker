package com.example.incomeexpensetracker.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.nav_routes
import com.example.incomeexpensetracker.ui.components.topBarScreen
import com.example.incomeexpensetracker.ui.expense.ExpenseViewModel
import me.bytebeats.views.charts.pie.PieChart
import me.bytebeats.views.charts.pie.PieChartData
import me.bytebeats.views.charts.pie.render.SimpleSliceDrawer
import me.bytebeats.views.charts.simpleChartAnimation

@Composable
fun homeScreen(navHostController: NavHostController) {

    val expenseViewModel : ExpenseViewModel = hiltViewModel()

    expenseViewModel.getTotalExpense()
    val totalExpense = expenseViewModel.totalExpense


    Scaffold(
        topBar = { topBarScreen() },
        floatingActionButton = {
            homeFloatingActionButton(navHostController = navHostController)
        }
    ) {

        Column {

            Row {
                pieChartView(totalExpense)

                Column(
                    modifier = Modifier.padding(10.dp)
                ) {

                    Text(
                        text = "Total Expense:",
                        color = Color(0xFFC21336.toInt()),
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "Total Income:",
                        color = Color(0xFF347539.toInt()),
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "Total Balance:",
                        color = Color(0xFF415575.toInt()),
                        modifier = Modifier.padding(5.dp)
                    )

                }
            }


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
            Row {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = Color(0xFF8C7558.toInt()),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            navHostController.navigate(nav_routes.expense_list)
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
            Row {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = Color(0xFF589A8D.toInt()),
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
fun pieChartView(totalExpense: Float) {
    PieChart(
        pieChartData = PieChartData(
            slices = listOf(
                PieChartData.Slice(
                    30F,
                    Color(0xFFC21336.toInt())
                ),
                PieChartData.Slice(
                    30F,
                    Color(0xFF347539.toInt())
                ),
                PieChartData.Slice(
                    40F,
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
        onClick = { navHostController.navigate(nav_routes.expense_add) },
        backgroundColor = Color(0xFFC22146.toInt())
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Account"
        )
    }
}