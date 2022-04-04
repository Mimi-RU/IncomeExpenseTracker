package com.example.incomeexpensetracker.ui.home


import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.nav_routes

@Composable
fun homeBottomBar(navHostController: NavHostController) {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 10.dp) {

        BottomNavigationItem(
            icon = {
                Icon(imageVector = Icons.Default.KeyboardArrowUp, "Incomes")
            },
            label = { Text(text = "Incomes") },
            selected = true,
            onClick = {}
        )

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.KeyboardArrowDown, "Expenses")
        },
            label = { Text(text = "Expenses") },
            selected = false,
            onClick = {
                selectedIndex.value = 1
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Person, "")
        },
            label = { Text(text = "Accounts") },
            selected = false,
            onClick = {
                navHostController.navigate(nav_routes.account_list)
            })
    }
}