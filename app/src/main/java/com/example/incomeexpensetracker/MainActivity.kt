package com.example.incomeexpensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.incomeexpensetracker.nav_arguments.account_id
import com.example.incomeexpensetracker.nav_arguments.category_id
import com.example.incomeexpensetracker.nav_arguments.expense_id
import com.example.incomeexpensetracker.nav_arguments.income_id
import com.example.incomeexpensetracker.nav_arguments.note_id
import com.example.incomeexpensetracker.nav_arguments.tag_id
import com.example.incomeexpensetracker.ui.account.accountAddScreen
import com.example.incomeexpensetracker.ui.account.accountEditScreen
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
private fun IncomeExpenseTrackerApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = nav_routes.home) {

        composable(route = nav_routes.home) {
            homeScreen(navHostController = navController)
        }

        composable(route = nav_routes.account_list) {
            accountListScreen(navHostController = navController)
        }

        composable(route = nav_routes.account_add) {
            accountAddScreen(navHostController = navController)
        }

        composable(route = nav_routes.account_edit) {
            val accountId = it.arguments!!.getString("account_id")!!.toInt()
            accountEditScreen(navHostController = navController, id = accountId)
        }

    }

}


object nav_routes {
    const val home = "home"
    const val account_list = "account_list"
    const val account_add = "account_add"
    const val account_edit = "account_edit/{$account_id}"

    const val income_add = "income_add"
    const val income_edit = "income_edit/{$income_id}"

    const val expense_list = "expense_list"
    const val expense_add = "expense_add"
    const val expense_edit = "expense_edit/{$expense_id}"

    const val category_list = "category_list"
    const val category_add = "category_add"
    const val category_edit = "category_edit/{$category_id}"

    const val note_list = "note_list"
    const val note_add = "note_add"
    const val note_edit = "note_edit/{$note_id}"

    const val tag_list = "tag_list"
    const val tag_add = "tag_add"
    const val tag_edit = "tag_edit/{$tag_id}"
}

object nav_arguments {
    const val account_id = "account_id"
    const val income_id = "income_id"
    const val expense_id = "expense_id"
    const val category_id = "category_id"
    const val note_id = "note_id"
    const val tag_id = "tag_id"
}
