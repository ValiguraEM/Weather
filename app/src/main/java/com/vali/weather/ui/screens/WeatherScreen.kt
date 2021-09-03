package com.vali.weather.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.vali.weather.ui.components.PlaceList
import com.vali.weather.ui.components.SearchBar
import com.vali.weather.ui.components.WeatherLocationList
import com.vali.weather.vm.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel, navController: NavController) {
    val textState = remember {
        mutableStateOf(TextFieldValue(""))
    }
    Scaffold(
        topBar = { SearchBar(textState) },
        content = {
            Box {
                WeatherLocationList(viewModel, navController)
                PlaceList(viewModel, textState)
            }
        }
    )
}