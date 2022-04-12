package com.example.incomeexpensetracker.ui.income

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.data.model.IncomeWithRelations
import com.example.incomeexpensetracker.nav_routes
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun incomeListScreen(navHostController: NavHostController) {

    val incomeViewModel: IncomeViewModel = hiltViewModel()

    LaunchedEffect(key1 = true) {
        incomeViewModel.getAllIncomesWithRelations()
    }
    val incomeFlow = incomeViewModel.allIncomesWithRelations.asStateFlow()
    val incomeList: List<IncomeWithRelations> = incomeFlow.value

    Scaffold(
        topBar = { incomeListtopBar(navHostController) },

        floatingActionButton = { incomeListfloatingActionButton(navHostController) }
    ) {

        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            items(incomeList) { item: IncomeWithRelations ->
                incomeItem(income = item)
            }
        }
    }
}


@Composable
fun incomeItem(income: IncomeWithRelations) {

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = income.category.name,
            modifier = Modifier
                .weight(0.3f)
                .border(0.2.dp, Color.Black)
                .padding(4.dp),
            textAlign = TextAlign.Center

        )
        Text(
            text = income.account.name,
            modifier = Modifier
                .weight(0.3f)
                .border(0.2.dp, Color.Black)
                .padding(4.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = income.income.amount,
            modifier = Modifier
                .weight(0.3f)
                .border(0.2.dp, Color.Black)
                .padding(4.dp),
            textAlign = TextAlign.Center
        )
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

