package com.vali.weather.model

import com.vali.weather.model.database.entity.Location
import com.vali.weather.model.weather.WeatherResponse

data class LocationWithWeather(
    val location: Location,
    val weather: WeatherResponse
)