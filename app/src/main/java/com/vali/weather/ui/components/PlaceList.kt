package com.vali.weather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import com.vali.weather.vm.WeatherViewModel

@Composable
fun PlaceList(viewModel: WeatherViewModel, state: MutableState<TextFieldValue>) {
    viewModel.getLocationNamePredictions(state.value.text)
    val predictions: List<String> = viewModel.placePredictions.collectAsState().value
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Blue)) {
        items(items = predictions) { prediction ->
            PlaceListItem(
                placeName = prediction,
                onItemClick = { selectedPlace ->
                    viewModel.handlePlaceSelected(selectedPlace)
                    state.value = TextFieldValue("")
                }
            )
        }
    }
}
