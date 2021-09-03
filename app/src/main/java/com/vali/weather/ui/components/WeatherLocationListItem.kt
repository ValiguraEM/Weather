package com.vali.weather.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.vali.weather.model.LocationWithWeather

@Composable
fun WeatherLocationListItem(
    locationWithWeather: LocationWithWeather,
    onItemClick: (String) -> Unit
) {
    Card(shape = RoundedCornerShape(8.dp), modifier = Modifier.padding(8.dp, 8.dp)) {
        Row(
            modifier = Modifier
                .clickable(onClick = { onItemClick(locationWithWeather.location.id) })
                .fillMaxWidth()
                .height(80.dp)
                .padding(PaddingValues(8.dp, 16.dp))
        ) {
            Text(
                text = locationWithWeather.location.name,
                fontSize = 36.sp,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(modifier = Modifier.padding(8.dp))
            GlideImage(
                imageModel = "${IMAGE_URL + locationWithWeather.weather.current.weather[0].icon}@4x.png",
                modifier = Modifier.size(48.dp, 48.dp)
            )
        }
    }
}