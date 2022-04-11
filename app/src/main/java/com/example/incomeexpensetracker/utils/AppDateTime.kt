package com.example.incomeexpensetracker.utils

import java.text.SimpleDateFormat
import java.util.*

data class AppDateTime(
    val dateFormat: String = "yyyy-MM-dd",
    val monthFormat: String = "LLL",
    val yearFormat: String = "yyyy"
) {
    var calender = Calendar.getInstance()

    //date
    private var _dateFormat = SimpleDateFormat(dateFormat)
    var date = _dateFormat.format(calender.time).toString()

    //month
    var _monthFormat = SimpleDateFormat(monthFormat)
    var month = _monthFormat.format(calender.time).toString()

    //year
    private var _yearFormat = SimpleDateFormat(yearFormat)
    var year = _yearFormat.format(calender.time).toString()
}
