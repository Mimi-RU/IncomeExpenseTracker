package com.example.incomeexpensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.incomeexpensetracker.nav_arguments.account_id
import com.example.incomeexpensetracker.ui.account.AccountViewModel
import com.example.incomeexpensetracker.ui.account.accountAddScreen
import com.example.incomeexpensetracker.ui.account.accountListScreen
import com.example.incomeexpensetracker.ui.home.homeScreen
import com.example.incomeexpensetracker.ui.theme.IncomeExpenseTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IncomeExpenseTrackerTheme {
                IncomeExpenseTrackerApp()
            }
        }
    }
}


@Composable
private fun IncomeExpenseTrackerApp(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination =nav_routes.home){
        
        composable(route = nav_routes.home){
            homeDestination(navController)
        }

        composable(route = nav_routes.account_list){
            accountListDestination(navController)
        }

        composable(route = nav_routes.account_add){
            accountAddDestination(navController)
        }
    }

}

@Composable
fun homeDestination(navController: NavHostController){
     homeScreen(navHostController = navController)
}

@Composable
fun accountListDestination(navController: NavHostController) {
    val  viewModel : AccountViewModel = hiltViewModel()
    accountListScreen(navHostController = navController, viewModel)
}


@Composable
fun accountAddDestination(navController: NavHostController) {
    val  viewModel : AccountViewModel = hiltViewModel()
    accountAddScreen(navHostController = navController, viewModel)
}


object nav_routes {
    const val home = "home"
    const val account_list = "account_list"
    const val account_add = "account_add"
    const val account_edit = "account_edit/{$account_id}"
    const val account_delete = "account_delete/{$account_id}"
}

object nav_arguments {
    const val account_id = "account_id"
}
