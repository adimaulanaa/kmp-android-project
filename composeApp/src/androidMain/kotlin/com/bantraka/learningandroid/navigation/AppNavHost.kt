package com.example.composenavigation.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bantraka.learningandroid.page.DetailScreen
import com.bantraka.learningandroid.page.HomeScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.route
    ) {
        composable(NavRoute.Home.route) {
            HomeScreen(
                onNavigateDetail = {
                    navController.navigate(NavRoute.Detail.route)
                }
            )
        }

        composable(NavRoute.Detail.route) {
            DetailScreen(
                onBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}
