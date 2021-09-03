package com.vali.weather.model.weather

data class Daily(
    val dt: Long,
    val temp: Temp,
    val weather: List<Weather>,
)