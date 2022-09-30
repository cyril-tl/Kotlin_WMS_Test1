package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Composable which receives counter as parameter
// passed down as arguments from home screen
@Composable
fun Setting(counter: String?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.Center
    ) {
        Column {

            Text(text = "Navigation with arguments", Modifier.padding(10.dp), color = Color.Black)

            // Display the counter
            Text(
                text = "Settings Screen, passed data is $counter",
                Modifier.padding(10.dp),
                color = Color.Black
            )
        }
    }
}