package com.vali.weather.model.repository

import com.vali.weather.BuildConfig
import com.vali.weather.model.network.WeatherService
import com.vali.weather.model.weather.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

const val OPENWEATHER_KEY = BuildConfig.OPENWEATHER_KEY

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService
) : WeatherRepository {
    override suspend fun getWeatherForPlace(lat: Double, lng: Double): Flow<WeatherResponse> {
        return flow {
            emit(
                weatherService.getWeather(lat, lng, apiKey = OPENWEATHER_KEY)
            )
        }.flowOn(Dispatchers.IO)
    }
}