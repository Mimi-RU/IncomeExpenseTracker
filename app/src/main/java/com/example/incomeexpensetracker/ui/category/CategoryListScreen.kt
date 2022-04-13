package com.example.incomeexpensetracker.ui.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.data.model.Category
import com.example.incomeexpensetracker.nav_routes
import com.example.incomeexpensetracker.ui.components.backTOHomeTopAppBar

@Composable
fun categoryListScreen(navHostController: NavHostController){

    val categoryViewModel : CategoryViewModel = hiltViewModel()

    LaunchedEffect(key1 = true){
        categoryViewModel.getAllCategories()
    }
    val categoryListState = categoryViewModel.allCategories.collectAsState()
    val categoryList = categoryListState.value

    Scaffold(
        topBar = {
            backTOHomeTopAppBar(
                title = "Categories",
                navHostController = navHostController
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navHostController.navigate(nav_routes.category_add)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Category"
                )
            }
        }
    ) {

        LazyColumn(
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(categoryList) { item: Category ->
                categoryItem(category = item, navHostController = navHostController)
            }
        }

    }
}

@Composable
fun categoryItem(category: Category, navHostController: NavHostController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { navHostController.navigate("category_edit/${category.id}") }
    ) {
        Text(
            text = category.type ?: "",
            modifier = Modifier
                .weight(0.5f),
            textAlign = TextAlign.Center
        )

        Text(
            text = category.name,
            modifier = Modifier
                .weight(0.5f),
            textAlign = TextAlign.Center
        )
    }

    Divider()
}

