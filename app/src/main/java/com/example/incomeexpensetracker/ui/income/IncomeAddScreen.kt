package com.example.incomeexpensetracker.ui.income

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.incomeexpensetracker.data.model.Account
import com.example.incomeexpensetracker.data.model.Tag
import com.example.incomeexpensetracker.nav_routes
import com.example.incomeexpensetracker.ui.account.AccountViewModel
import com.example.incomeexpensetracker.ui.tag.TagViewModel

@Composable
fun incomeAddScreen(navHostController: NavHostController) {

    val incomeViewModel: IncomeViewModel = hiltViewModel()
    val accountViewModel: AccountViewModel = hiltViewModel()
    val tagViewModel: TagViewModel = hiltViewModel()

    // account list
    LaunchedEffect(key1 = true) {
        accountViewModel.getAllAccounts()
    }
    val accountStateList = accountViewModel.allAccounts.collectAsState()
    val accountList = accountStateList.value

    // tag list
    LaunchedEffect(key1 = true) {
        tagViewModel.getAllTags()
    }
    val tagStateList = tagViewModel.allTags.collectAsState()
    val tagList = tagStateList.value

    val account by incomeViewModel.account
    val tag by incomeViewModel.tag
    val amount by incomeViewModel.amount


    Scaffold(
        topBar = {
            incomeAddTopBar(
                navHostController,
                incomeViewModel = incomeViewModel
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            selectAccount(
                account = account,
                onAccountChange = {
                    incomeViewModel.account.value = it
                },
                accountList = accountList,
                navHostController = navHostController
            )

            selectIncomeSource(
                tag = tag,
                onTagChange = {
                    incomeViewModel.tag.value = it
                },
                tagList = tagList,
                navHostController = navHostController
            )

            enterAmount(
                amount = amount,
                onAmountChange = {
                    incomeViewModel.amount.value = it
                }
            )
        }
    }
}


@Composable
fun enterAmount(
    amount: String,
    onAmountChange: (String) -> Unit
) {
    OutlinedTextField(
        value = amount,
        onValueChange = { onAmountChange(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Enter Amount") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )
}

@Composable
fun selectAccount(
    account: Account?,
    onAccountChange: (Account) -> Unit,
    accountList: List<Account>,
    navHostController: NavHostController
) {

    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { expanded = true }
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurface
            )
    ) {

        Text(text = account?.name ?: "Select Account")

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {

            accountList.onEach { account ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onAccountChange(account)
                    }
                ) {

                    Text(text = account.name)

                }
            }

            DropdownMenuItem(
                onClick = {
                    navHostController.navigate(nav_routes.account_add)
                }
            ) {

                Text(text = "Add New Account")

            }
        }
    }
}

@Composable
fun selectIncomeSource(
    tag: Tag?,
    onTagChange: (Tag) -> Unit,
    tagList: List<Tag>,
    navHostController: NavHostController
) {

    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { expanded = true }
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurface
            )
    ) {

        Text(text = tag?.name ?: "Select Income Source")

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {

            tagList.onEach { tag ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onTagChange(tag)
                    }
                ) {

                    Text(text = tag.name)

                }
            }

            DropdownMenuItem(
                onClick = {
                    navHostController.navigate(nav_routes.tag_add)
                }
            ) {

                Text(text = "Add New Tag")

            }
        }
    }
}

@Composable
fun incomeAddTopBar(navHostController: NavHostController, incomeViewModel: IncomeViewModel) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navHostController.navigate(nav_routes.income_list) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to Income List"
                )
            }
        },
        title = {
            Text(text = "Add Income")
        },
        actions = {

            IconButton(onClick = {
                incomeViewModel.storeIncome()
                navHostController.navigate(nav_routes.income_list)
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save Income")
            }

        }

    )
}
