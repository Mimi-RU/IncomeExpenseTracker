package com.example.incomeexpensetracker.utils

import java.text.SimpleDateFormat
import java.util.*

data class AppDateTime(
    val dayFormat: String = "EEE",
    val dateFormat: String = "yyyy-MM-dd",
    val monthFormat: String = "LLL",
    val yearFormat: String = "yyyy"
) {
    private var calender = Calendar.getInstance()

    //date
    private var _dateFormat = SimpleDateFormat(dateFormat)
    var date = _dateFormat.format(calender.time).toString()

    //month
    var _monthFormat = SimpleDateFormat(monthFormat)
    var month = _monthFormat.format(calender.time).toString()

    //year
    private var _yearFormat = SimpleDateFormat(yearFormat)
    var year = _yearFormat.format(calender.time).toString()

    // days of Week
    val daysOfWeek = listOf<String>("Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri")

    // hours of Day
    val hoursOfDay = listOf<Int>(
        0,
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
        9,
        10,
        11,
        12,
        13,
        14,
        15,
        16,
        17,
        18,
        19,
        20,
        21,
        22,
        23
    )

    // dates of Month
    val datesOfMonth = listOf<Int>(
        0,
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
        9,
        10,
        11,
        12,
        13,
        14,
        15,
        16,
        17,
        18,
        19,
        20,
        21,
        22,
        23,
        24,
        25,
        26,
        27,
        28,
        29,
        30,
        31
    )

    // months of Year
    val monthsOfYear = listOf<String>(
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "Jun",
        "Jul",
        "Aug",
        "Sep",
        "Oct",
        "Nov",
        "Dec"
    )

}
