package com.vali.weather.model.location

import com.google.gson.annotations.SerializedName

data class GeocodeResponse(
    val results: List<Result>,
    val status: String
)

data class Result(
    @SerializedName("formatted_address")
    val formattedAddress: String,
    val geometry: Geometry,
    @SerializedName("place_id")
    val placeId: String
)

data class Geometry(
    val location: Location,
)

data class Location(
    val lat: Double,
    val lng: Double
)