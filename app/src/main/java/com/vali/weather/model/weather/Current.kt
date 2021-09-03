package com.vali.weather.model.weather

data class Current(
    val dt: Long,
    val temp: Double,
    val weather: List<Weather>,
)