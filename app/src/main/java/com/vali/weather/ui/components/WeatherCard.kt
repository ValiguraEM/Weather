package com.vali.weather.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.vali.weather.model.weather.Current
import com.vali.weather.model.weather.DailyWeather
import java.text.SimpleDateFormat
import java.util.*

const val IMAGE_URL = "https://openweathermap.org/img/wn/"

@Composable
fun WeatherCardLarge(cityName: String, currentWeather: Current) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = cityName,
            fontSize = 36.sp,
            fontWeight = FontWeight.Black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        val sdf = SimpleDateFormat("EEEE HH:mm", Locale.getDefault())
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = sdf.format(currentWeather.dt * 1000), fontSize = 20.sp)
        Spacer(modifier = Modifier.padding(4.dp))
        GlideImage(
            imageModel = "${IMAGE_URL + currentWeather.weather[0].icon}@4x.png",
            modifier = Modifier.size(128.dp, 128.dp)
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text = getFormattedTemperature(currentWeather.temp), fontSize = 24.sp)
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text = currentWeather.weather[0].main, fontSize = 24.sp)
    }
}

@Composable
fun WeatherCard(dailyWeather: DailyWeather) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(text = dailyWeather.formattedDate, fontSize = 20.sp)
        GlideImage(imageModel = "${IMAGE_URL + dailyWeather.icon}@4x.png")
        Text(text = getFormattedTemperature(dailyWeather.temperature))
        Text(text = dailyWeather.weather)
    }
}

private fun getFormattedTemperature(temperature: Double): String = "${temperature.toInt()}°C"