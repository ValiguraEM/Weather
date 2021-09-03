package com.vali.weather.model.repository

import com.vali.weather.BuildConfig
import com.vali.weather.model.database.Database
import com.vali.weather.model.database.entity.Location
import com.vali.weather.model.location.Result
import com.vali.weather.model.network.LocationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

const val API_KEY = BuildConfig.API_KEY

class LocationRepositoryImpl
@Inject constructor(
    private val locationService: LocationService,
    private val db: Database
) : LocationRepository {

    override suspend fun getLocationNamePredictions(userInput: String): Flow<List<String>> {
        return flow {
            emit(
                locationService.searchForPlace(
                    userInput,
                    API_KEY
                ).predictions.map { it.description }
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getGeocodeForPlace(address: String): Flow<Result> {
        return flow {
            emit(
                locationService.getCoordinatesForPlace(
                    address,
                    API_KEY
                ).results[0]
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getLocationsFromDb(): Flow<List<Location>> =
        db.locationDao().getLocations()


    override suspend fun insertLocationsToDb(vararg locations: Location) =
        db.locationDao().insertAll(*locations)

}