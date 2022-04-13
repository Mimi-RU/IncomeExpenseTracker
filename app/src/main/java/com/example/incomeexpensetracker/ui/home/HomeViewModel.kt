package com.example.incomeexpensetracker.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.incomeexpensetracker.utils.AppDateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor()  : ViewModel() {
    val appDateTime = AppDateTime()
    val selectedTab : MutableState<String> = mutableStateOf("Monthly")
    val thisMonth : MutableState<String> = mutableStateOf(appDateTime.month)
    val thisYear: MutableState<String> = mutableStateOf(appDateTime.year)
}