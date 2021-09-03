package com.vali.weather.di

import com.google.gson.GsonBuilder
import com.vali.weather.model.network.LocationService
import com.vali.weather.model.network.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): GsonConverterFactory = GsonConverterFactory.create(GsonBuilder().create())

    @Singleton
    @Provides
    fun provideLocationService(gsonConverterFactory: GsonConverterFactory): LocationService {
        return Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/")
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(LocationService::class.java)
    }

    @Singleton
    @Provides
    fun provideWeatherService(gsonConverterFactory: GsonConverterFactory): WeatherService {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(WeatherService::class.java)
    }
}