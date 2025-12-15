package com.example.composenavigation.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bantraka.learningandroid.core.di.AppModule
import com.bantraka.learningandroid.page.ComponentsPage
import com.bantraka.learningandroid.page.dashboard.pages.DashboardPage
import com.bantraka.learningandroid.page.DetailScreen
import com.bantraka.learningandroid.page.HomeScreen
import com.bantraka.learningandroid.page.ProfilePage

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
                },
                onNavigateDashboard = {
                    navController.navigate(NavRoute.Dashboard.route)
                },
                onNavigateProfile = {
                    navController.navigate(NavRoute.Profile.route)
                },
                onNavigateComponents = {
                    navController.navigate(NavRoute.Components.route)
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

        composable(NavRoute.Dashboard.route) {
            val dashboardViewModel = AppModule.provideDashboardViewModel()

            DashboardPage(
                viewModel = dashboardViewModel,
                onBack = { navController.navigateUp() }
            )
        }

        composable(NavRoute.Profile.route) {
            ProfilePage (
                onBack = {
                    navController.navigateUp()
                }
            )
        }

        composable(NavRoute.Components.route) {
            ComponentsPage (
                onBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}
