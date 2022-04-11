package com.example.incomeexpensetracker.ui.income

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.data.model.Income
import com.example.incomeexpensetracker.nav_routes
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun incomeListScreen(navHostController: NavHostController) {

    val incomeViewModel: IncomeViewModel = hiltViewModel()

    LaunchedEffect(key1 = true) {
        incomeViewModel.getAllIncomes()
    }
    val incomeFlow = incomeViewModel.allIncomes.asStateFlow()
    val incomeList: List<Income> = incomeFlow.value

    Scaffold(
        topBar = { incomeListtopBar(navHostController) },

        floatingActionButton = { incomeListfloatingActionButton(navHostController) }
    ) {

        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            items(incomeList) { item: Income ->
                incomeItem(income = item)
            }
        }
    }
}

@Composable
fun incomeItem(income: Income) {
    Column(
        modifier = Modifier
            .padding(12.dp)
    ) {

        Text(text = income.tag_id.toString())
        Text(text = income.account_id.toString())
        Text(text = income.amount)

    }
}

@Composable
fun incomeListfloatingActionButton(navHostController: NavHostController) {

    FloatingActionButton(onClick = { navHostController.navigate(nav_routes.income_add) }) {

        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Income"
        )
    }

}

@Composable
fun incomeListtopBar(navHostController: NavHostController) {
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
            Text(text = "Incomes")
        }
    )
}

