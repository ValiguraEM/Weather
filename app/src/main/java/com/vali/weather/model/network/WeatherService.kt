package com.vali.weather.model.network

import com.vali.weather.model.weather.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("onecall?")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lng: Double,
        @Query("units") units: String = "metric",
        @Query("exclude") exclude: String = "minutely,hourly,alerts",
        @Query("appid") apiKey: String
    ): WeatherResponse
}