package com.vali.weather.model.repository

import com.vali.weather.model.database.entity.Location
import com.vali.weather.model.location.Result
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getLocationNamePredictions(userInput: String): Flow<List<String>>

    suspend fun getGeocodeForPlace(address: String): Flow<Result>

    suspend fun getLocationsFromDb(): Flow<List<Location>>

    suspend fun insertLocationsToDb(vararg locations: Location)
}