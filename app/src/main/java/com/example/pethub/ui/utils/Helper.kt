package com.example.pethub.ui.utils

import com.example.pethub.ui.navigation.Screen

fun Int.toTime(): String {
    val hour = this / 60
    val minute = this % 60

    if (hour == 0) {
        return "$minute min(s) ago"
    }

    return "$hour hour(s) $minute min(s) ago"
}

fun String?.showTopBar(): Boolean {
    return when (this) {
        Screen.Home.route -> true
        Screen.List.route -> true
        Screen.Detail.route -> true
        Screen.About.route -> true
        else -> false
    }
}