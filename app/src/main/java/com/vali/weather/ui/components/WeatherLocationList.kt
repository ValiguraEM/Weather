package com.vali.weather.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vali.weather.model.LocationWithWeather
import com.vali.weather.ui.screens.Screen
import com.vali.weather.vm.WeatherViewModel

@Composable
fun WeatherLocationList(viewModel: WeatherViewModel, navController: NavController) {
    val locationsWithWeather: List<LocationWithWeather> =
        viewModel.locationsWithWeather.collectAsState().value.distinctBy { it.location.id }
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        items(items = locationsWithWeather) { locationWithWeather ->
            WeatherLocationListItem(
                locationWithWeather = locationWithWeather,
                onItemClick = { selectedPlace ->
                    navController.navigate(Screen.WeatherDetailScreen.withArgs(selectedPlace))
                }
            )
        }
    }
}