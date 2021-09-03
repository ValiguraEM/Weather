package com.vali.weather.model.weather

data class DailyWeather(
    val formattedDate: String,
    val temperature: Double,
    val weather: String,
    val icon: String
)
