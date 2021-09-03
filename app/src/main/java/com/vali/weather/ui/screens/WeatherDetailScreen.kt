package com.vali.weather.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.vali.weather.model.weather.DailyWeather
import com.vali.weather.ui.components.WeatherCard
import com.vali.weather.ui.components.WeatherCardLarge
import com.vali.weather.vm.WeatherViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WeatherDetailScreen(viewModel: WeatherViewModel, locationId: String) {
    val locationWithWeather =
        viewModel.locationsWithWeather.collectAsState().value.filter { it.location.id == locationId }[0]
    val sdf = SimpleDateFormat("EEEE dd MMM", Locale.getDefault())
    val weather = locationWithWeather.weather
    val currentWeather = weather.current
    val filtered = weather.daily.subList(1, 4)

    Column(
        modifier = Modifier.background(
            Brush.verticalGradient(getColorGradient(currentWeather.weather[0].main))
        )
    ) {
        Row(modifier = Modifier.weight(0.7f)) {
            WeatherCardLarge(locationWithWeather.location.name, currentWeather)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f), horizontalArrangement = Arrangement.Center
        ) {
            filtered.forEach {
                val daily = DailyWeather(
                    sdf.format(it.dt * 1000),
                    it.temp.day,
                    it.weather[0].main,
                    it.weather[0].icon
                )
                Row(modifier = Modifier.weight(1.0f)) {
                    WeatherCard(daily)
                }
            }
        }
    }
}

fun getColorGradient(status: String): List<Color> {
    return when (status) {
        "Clear" -> listOf(Color.Cyan, Color.Blue)
        else -> listOf(Color.LightGray, Color.Gray)
    }
}