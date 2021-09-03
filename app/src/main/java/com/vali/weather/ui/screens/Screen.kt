package com.vali.weather.ui.screens

sealed class Screen(val route: String) {
    object WeatherScreen : Screen("weather")
    object WeatherDetailScreen : Screen("detail")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}
