package com.vali.weather.model.network

import com.vali.weather.model.location.GeocodeResponse
import com.vali.weather.model.location.PlacesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {

    @GET("place/autocomplete/json?")
    suspend fun searchForPlace(
        @Query("input") input: String,
        @Query("key") apiKey: String
    ): PlacesResponse

    @GET("geocode/json?")
    suspend fun getCoordinatesForPlace(
        @Query("address") address: String,
        @Query("key") apiKey: String
    ): GeocodeResponse
}