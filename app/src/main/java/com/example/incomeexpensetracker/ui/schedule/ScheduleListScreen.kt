package com.example.incomeexpensetracker.ui.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.data.model.ScheduleWithRelation
import com.example.incomeexpensetracker.nav_routes


@Composable
fun scheduleListScreen(navHostController: NavHostController) {

    val scheduleViewModel: ScheduleViewModel = hiltViewModel()

    LaunchedEffect(key1 = true) {
        scheduleViewModel.getAllSchedule()
    }

    val scheduleListState = scheduleViewModel.allSchedule.collectAsState()

    val scheduleList = scheduleListState.value

    Scaffold(
        topBar = { scheduleListTopBar(navHostController = navHostController) },
        floatingActionButton = { scheduleListFloatingButton(navHostController = navHostController) }
    ) {

        Column {

            scheduleList.forEach {
                scheduleItemScreen(it)
            }

        }
    }
}

@Composable
fun scheduleItemScreen(item: ScheduleWithRelation) {

    Row {
        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color(0xFFA3AB78.toInt()),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Type",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(16.dp)
            )

            Text(
                text = "Account",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(16.dp)
            )

            Text(
                text = "Amount",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(16.dp)
            )

            Text(
                text = "Interval",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(16.dp)
            )

            Text(
                text = "Repeat",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(16.dp)
            )

            Text(
                text = "Notice Time",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(16.dp)
            )

        }
    }

}

@Composable
fun scheduleListFloatingButton(navHostController: NavHostController) {

    FloatingActionButton(onClick = { navHostController.navigate(nav_routes.schedule_add) }) {

        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Schedule"
        )
    }
}

@Composable
fun scheduleListTopBar(navHostController: NavHostController) {
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
            Text(text = "Schedules")
        }
    )
}
