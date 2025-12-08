package com.example.composenavigation.core.navigation

sealed class NavRoute(val route: String) {
    data object Home : NavRoute("home")
    data object Detail : NavRoute("detail")
}