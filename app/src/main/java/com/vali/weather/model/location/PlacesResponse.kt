package com.vali.weather.model.location

data class PlacesResponse(
    val predictions: List<Prediction>,
    val status: String
)

data class Prediction(
    val description: String
)