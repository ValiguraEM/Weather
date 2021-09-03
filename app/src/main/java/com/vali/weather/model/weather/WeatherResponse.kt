package com.vali.weather.model.weather

data class WeatherResponse(
    val current: Current,
    val daily: List<Daily>
)