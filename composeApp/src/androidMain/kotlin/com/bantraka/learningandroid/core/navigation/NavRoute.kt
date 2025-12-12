package com.example.composenavigation.core.navigation

sealed class NavRoute(val route: String) {
    data object Home : NavRoute("home")
    data object Dashboard : NavRoute("dashboard")
    data object Profile : NavRoute("profile")
    data object Detail : NavRoute("detail")
    data object Components : NavRoute("components")
}