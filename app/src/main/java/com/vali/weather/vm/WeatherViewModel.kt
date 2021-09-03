package com.vali.weather.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vali.weather.model.LocationWithWeather
import com.vali.weather.model.database.entity.Location
import com.vali.weather.model.repository.LocationRepository
import com.vali.weather.model.repository.WeatherRepository
import com.vali.weather.model.weather.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val coroutineContext = Dispatchers.IO
    private val _placePredictions = MutableStateFlow<List<String>>(emptyList())
    val placePredictions: StateFlow<List<String>> = _placePredictions

    private val _dbLocations = MutableStateFlow<List<Location>>(emptyList())

    private val _locationsWithWeather = MutableStateFlow<List<LocationWithWeather>>(emptyList())
    val locationsWithWeather: StateFlow<List<LocationWithWeather>> = _locationsWithWeather

    private val _weatherResponse = MutableSharedFlow<Pair<Location, WeatherResponse>>()

    init {
        getLocationsFromDb()
        getWeather()
    }

    fun getLocationNamePredictions(userInput: String) {
        viewModelScope.launch(coroutineContext) {
            locationRepository.getLocationNamePredictions(userInput)
                .catch { exception -> exception.message?.let { Log.e("E", it) } }
                .collect { response ->
                    _placePredictions.value = response
                }
        }
    }

    fun handlePlaceSelected(address: String) {
        viewModelScope.launch(coroutineContext) {
            locationRepository.getGeocodeForPlace(address)
                .catch { exception -> exception.message?.let { Log.e("E", it) } }
                .collect { response ->
                    val geometryLocation = response.geometry.location
                    val location = Location(
                        id = response.placeId,
                        name = response.formattedAddress,
                        lat = geometryLocation.lat,
                        lng = geometryLocation.lng
                    )
                    saveToDB(location)
                    getWeatherForLocation(location)
                }
        }
    }

    private suspend fun saveToDB(location: Location) {
        viewModelScope.launch(coroutineContext) {
            locationRepository.insertLocationsToDb(location)
        }
    }

    private fun getLocationsFromDb() {
        viewModelScope.launch(coroutineContext) {
            locationRepository.getLocationsFromDb()
                .catch { exception -> exception.message?.let { Log.e("E", it) } }
                .collect { locations ->
                    _dbLocations.value = locations
                    locations.forEach {
                        getWeatherForLocation(it)
                    }
                }
        }
    }

    private fun getWeatherForLocation(location: Location) {
        viewModelScope.launch(coroutineContext) {
            weatherRepository.getWeatherForPlace(location.lat, location.lng)
                .catch { exception -> exception.message?.let { Log.e("E", it) } }
                .collect { weatherResponse ->
                    _weatherResponse.emit(Pair(location, weatherResponse))
                }
        }
    }

    private fun getWeather() {
        viewModelScope.launch {
            _weatherResponse.collect { pair ->
                _locationsWithWeather.updateAndGet {
                    it + LocationWithWeather(
                        pair.first,
                        pair.second
                    )
                }
            }
        }
    }

    private inline fun <T> MutableStateFlow<T>.updateAndGet(function: (T) -> T): T {
        while (true) {
            val prevValue = value
            val nextValue = function(prevValue)
            if (compareAndSet(prevValue, nextValue)) {
                return nextValue
            }
        }
    }
}