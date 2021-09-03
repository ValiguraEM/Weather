package com.vali.weather.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.vali.weather.ui.screens.Screen
import com.vali.weather.ui.screens.WeatherDetailScreen
import com.vali.weather.ui.screens.WeatherScreen
import com.vali.weather.vm.WeatherViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<WeatherViewModel>()
    NavHost(navController = navController, startDestination = Screen.WeatherScreen.route) {
        composable(Screen.WeatherScreen.route) {
            WeatherScreen(viewModel, navController)
        }
        composable(
            route = Screen.WeatherDetailScreen.route + "/{locationId}",
            arguments = listOf(navArgument("locationId") {
                type = NavType.StringType
                nullable = false
            })
        ) { entry ->
            entry.arguments?.getString("locationId")?.let { locationId ->
                WeatherDetailScreen(viewModel, locationId)
            }
        }
    }
}