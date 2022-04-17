package com.example.incomeexpensetracker.ui.income

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
        topBar = { IncomeListTopBar(navHostController) },

        floatingActionButton = { IncomeListFloatingActionButton(navHostController) }
    ) {

        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            items(incomeList) { item: IncomeWithRelations ->
                incomeItemContent(income = item, navHostController)
            }
        }
    }
}


@Composable
fun incomeItemContent(income: IncomeWithRelations, navHostController: NavHostController){

    Row(
        modifier = Modifier.clickable {
            navHostController.navigate("income_edit/${income.income.id}")
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
                    text = "Category: " + income.category.name,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(4.dp)
                )

                Text(
                    text = "Amount : " + income.income.amount,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(4.dp)
                )

                Text(
                    text = "Account : " + income.account.name,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(4.dp)
                )

                Text(
                    text = "Date: " + income.income.date,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun IncomeListFloatingActionButton(navHostController: NavHostController) {

    FloatingActionButton(onClick = { navHostController.navigate(nav_routes.income_add) }) {

        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Income"
        )
    }

}

@Composable
fun IncomeListTopBar(navHostController: NavHostController) {
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

