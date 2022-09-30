package com.example.myapplication.routes

// It contains route names to all three screens
sealed class Routes(val route: String) {
    object Home : Routes("home")
    object PageECEC : Routes("pageECEC")
}