package com.vali.weather.di

import com.vali.weather.model.database.Database
import com.vali.weather.model.network.LocationService
import com.vali.weather.model.network.WeatherService
import com.vali.weather.model.repository.LocationRepository
import com.vali.weather.model.repository.LocationRepositoryImpl
import com.vali.weather.model.repository.WeatherRepository
import com.vali.weather.model.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideLocationRepository(
        locationService: LocationService,
        db: Database
    ): LocationRepository = LocationRepositoryImpl(locationService, db)

    @Singleton
    @Provides
    fun provideWeatherRepository(weatherService: WeatherService): WeatherRepository =
        WeatherRepositoryImpl(weatherService)
}