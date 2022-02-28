package com.example.incomeexpensetracker.ui.home

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.nav_routes
import com.example.incomeexpensetracker.ui.components.bottomBar
import com.example.incomeexpensetracker.ui.components.topBarScreen

@Composable
fun homeScreen(navHostController: NavHostController) {

    Scaffold(
        topBar = { topBarScreen()},
        bottomBar = { bottomBar()},
        floatingActionButton = { homeFloatingActionButton(navHostController = navHostController)}
    ) {
        
        Text(text = "This is home page")

    }

}


@Composable
fun homeFloatingActionButton(navHostController: NavHostController){
    FloatingActionButton(onClick = { navHostController.navigate(nav_routes.account_add) }, ){
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Account" )
    }
}