package com.vali.weather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PlaceListItem(placeName: String, onItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onItemClick(placeName) })
            .height(60.dp)
            .fillMaxWidth()
            .padding(PaddingValues(8.dp, 16.dp))
            .background(Color.Blue)
    ) {
        Icon(imageVector = Icons.Default.LocationOn, contentDescription = "")
        Text(
            text = placeName,
            fontSize = 18.sp,
            color = Color.White,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}