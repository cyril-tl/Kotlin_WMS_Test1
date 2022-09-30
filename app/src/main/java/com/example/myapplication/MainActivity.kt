package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.models.ApiHelper
import com.example.myapplication.screens.HomeScreen
import com.example.myapplication.screens.SecondScreen

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MyApplicationTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                }
//            }
//        }
//    }
//}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colors.background) {
                ScreenMain()
            }
        }
    }
}

@Composable
fun ScreenMain() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        /* creating route "home" */
        composable(route = "home") {
            /* Using composable function */
            HomeScreen(navController)
        }
        composable(
            route = "second/{content}",
            arguments = listOf(
                navArgument("content") {
                    /* configuring arguments for navigation */
                    type = NavType.StringType
                }
            )
        ) {
            SecondScreen(
                navController,
                ApiHelper.resECEC
            )
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MyApplicationTheme {
//        Greeting("Android")
//    }
//}