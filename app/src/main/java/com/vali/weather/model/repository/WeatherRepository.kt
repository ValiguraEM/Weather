package com.vali.weather.model.repository

import com.vali.weather.model.weather.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getWeatherForPlace(lat: Double, lng: Double): Flow<WeatherResponse>
}