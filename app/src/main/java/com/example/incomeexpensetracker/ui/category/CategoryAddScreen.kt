package com.example.incomeexpensetracker.ui.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.nav_routes
import com.example.incomeexpensetracker.ui.components.enterText
import com.example.incomeexpensetracker.ui.components.selectItem
import com.example.incomeexpensetracker.utils.CategoryType

@Composable
fun categoryAddScreen(navHostController: NavHostController) {

    val categoryViewModel: CategoryViewModel = hiltViewModel()

    val name by categoryViewModel.name
    val type by categoryViewModel.type
    val listOfTypes = CategoryType.values().map { it.toString() }.toList()

    Scaffold(
        topBar = {
            categoryAddTopBar(
                navHostController = navHostController,
                categoryViewModel = categoryViewModel
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            // type
            Text(text = "Type")
            selectItem(
                item = type,
                onItemChange = {
                    categoryViewModel.type.value = it
                },
                itemList = listOfTypes
            )

            // name
            enterText(
                label = "Name",
                value = name,
                onValueChange = {
                    categoryViewModel.name.value = it
                }
            )
        }

    }
}

@Composable
fun categoryAddTopBar(navHostController: NavHostController, categoryViewModel: CategoryViewModel) {

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navHostController.navigate(nav_routes.category_list) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to Category List"
                )
            }
        },
        title = {
            Text(text = "Add Category")
        },
        actions = {

            IconButton(onClick = {
                categoryViewModel.storeCategory()
                navHostController.navigate(nav_routes.category_list)
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save Category"
                )
            }

        }

    )
}
