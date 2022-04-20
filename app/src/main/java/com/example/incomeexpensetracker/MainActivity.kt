package com.example.incomeexpensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.incomeexpensetracker.nav_arguments.account_id
import com.example.incomeexpensetracker.nav_arguments.category_id
import com.example.incomeexpensetracker.nav_arguments.expense_id
import com.example.incomeexpensetracker.nav_arguments.income_id
import com.example.incomeexpensetracker.nav_arguments.note_id
import com.example.incomeexpensetracker.nav_arguments.schedule_id
import com.example.incomeexpensetracker.notification.ScheduleNotificationBuilder
import com.example.incomeexpensetracker.ui.account.accountAddScreen
import com.example.incomeexpensetracker.ui.account.accountEditScreen
import com.example.incomeexpensetracker.ui.account.accountListScreen
import com.example.incomeexpensetracker.ui.category.categoryAddScreen
import com.example.incomeexpensetracker.ui.category.categoryEditScreen
import com.example.incomeexpensetracker.ui.category.categoryListScreen
import com.example.incomeexpensetracker.ui.expense.expenseAddScreen
import com.example.incomeexpensetracker.ui.expense.expenseEditScreen
import com.example.incomeexpensetracker.ui.expense.expenseListScreen
import com.example.incomeexpensetracker.ui.home.homeScreen
import com.example.incomeexpensetracker.ui.income.incomeAddScreen
import com.example.incomeexpensetracker.ui.income.incomeEditScreen
import com.example.incomeexpensetracker.ui.income.incomeListScreen
import com.example.incomeexpensetracker.ui.note.noteAddScreen
import com.example.incomeexpensetracker.ui.note.noteEditScreen
import com.example.incomeexpensetracker.ui.note.noteListScreen
import com.example.incomeexpensetracker.ui.schedule.scheduleAddScreen
import com.example.incomeexpensetracker.ui.schedule.scheduleEditScreen
import com.example.incomeexpensetracker.ui.schedule.scheduleListScreen
import com.example.incomeexpensetracker.ui.theme.IncomeExpenseTrackerTheme
import com.example.incomeexpensetracker.worker.PendingTransactionWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val notificationBuilder = ScheduleNotificationBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notificationBuilder.createNotificationChannel(this)

        // WorkManger
        val notificationWorkRequest: WorkRequest =
            PeriodicWorkRequestBuilder<PendingTransactionWorker>(1, TimeUnit.HOURS)
                .build()
        WorkManager
            .getInstance(this)
            .enqueue(notificationWorkRequest)

        // notificationBuilder.showNotification(this)
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

        composable(route = nav_routes.category_list) {
            categoryListScreen(navHostController = navController)
        }

        composable(route = nav_routes.category_add) {
            categoryAddScreen(navHostController = navController)
        }

        composable(route = nav_routes.category_edit) {
            val categoryId = it.arguments!!.getString("category_id")!!.toInt()
            categoryEditScreen(navHostController = navController, id = categoryId)
        }

        composable(route = nav_routes.expense_list) {
            expenseListScreen(navHostController = navController)
        }

        composable(route = nav_routes.expense_add) {
            expenseAddScreen(navHostController = navController)
        }

        composable(route = nav_routes.expense_edit) {
            val expenseId = it.arguments!!.getString("expense_id")!!.toInt()
            expenseEditScreen(navHostController = navController, id = expenseId)
        }
        composable(route = nav_routes.income_list) {
            incomeListScreen(navHostController = navController)
        }

        composable(route = nav_routes.income_add) {
            incomeAddScreen(navHostController = navController)
        }

        composable(route = nav_routes.income_edit) {
            val incomeId = it.arguments!!.getString("income_id")!!.toInt()
            incomeEditScreen(navHostController = navController, id = incomeId)
        }

        composable(route = nav_routes.note_list) {
            noteListScreen(navHostController = navController)
        }

        composable(route = nav_routes.note_add) {
            noteAddScreen(navHostController = navController)
        }

        composable(route = nav_routes.note_edit) {
            val noteId = it.arguments!!.getString("note_id")!!.toInt()
            noteEditScreen(navHostController = navController, id = noteId)
        }

        composable(route = nav_routes.schedule_list) {
            scheduleListScreen(navHostController = navController)
        }

        composable(route = nav_routes.schedule_add) {
            scheduleAddScreen(navHostController = navController)
        }

        composable(route = nav_routes.schedule_edit) {
            val scheduleId = it.arguments!!.getString("schedule_id")!!.toInt()
            scheduleEditScreen(navHostController = navController, id = scheduleId)
        }

    }

}


object nav_routes {
    const val home = "home"
    const val account_list = "account_list"
    const val account_add = "account_add"
    const val account_edit = "account_edit/{$account_id}"

    const val income_list = "income_list"
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

    const val schedule_list = "schedule_list"
    const val schedule_add = "schedule_add"
    const val schedule_edit = "schedule_edit/{$schedule_id}"
}

object nav_arguments {
    const val account_id = "account_id"
    const val income_id = "income_id"
    const val expense_id = "expense_id"
    const val category_id = "category_id"
    const val note_id = "note_id"
    const val schedule_id = "schedule_id"
}
