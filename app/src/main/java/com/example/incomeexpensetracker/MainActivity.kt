package com.example.incomeexpensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.incomeexpensetracker.nav_arguments.account_id
import com.example.incomeexpensetracker.ui.components.bottomBar
import com.example.incomeexpensetracker.ui.components.topBarScreen
import com.example.incomeexpensetracker.ui.home.homeScreen
import com.example.incomeexpensetracker.ui.theme.IncomeExpenseTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IncomeExpenseTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    IncomeExpenseTrackerApp()
                }
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
    }

}

@Composable
fun homeDestination(navController: NavHostController){
    homeScreen(navHostController = navController)
}

@Composable
fun accountListDestination(navController: NavHostController) {

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




@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")

    Card(modifier = Modifier.padding(24.dp)) {
        Row() {

            Column() {
                Text(text = "Column One")
            }

            Column() {
                Text(text = "Column Two")
            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IncomeExpenseTrackerTheme {
        Greeting("Android")
    }
}