package com.example.myapplication.screens

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.myapplication.models.ApiHelper
import com.example.myapplication.models.ECECModel
import kotlinx.coroutines.delay
import kotlin.system.measureTimeMillis

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(navController: NavController) {
//    var text by remember { mutableStateOf("azerty") }
    var resECEC by remember { mutableStateOf(ApiHelper.resECEC) }

    var text2 by remember { mutableStateOf(TextFieldValue("Text")) }
//    val focusManager = LocalFocusManager.current
    // initialize focus reference to be able to request focus programmatically
    val focusRequester = remember { FocusRequester() }
    val inputService = LocalTextInputService.current
    val focus = remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LaunchedEffect("") {
            println("launched effect")
            delay(300)
            inputService?.showSoftwareKeyboard()
            focusRequester.requestFocus()
            keyboardController?.hide()
        }
        TextField(
            value = text2,
            onValueChange = { text2 = it },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.onBackground,
                backgroundColor = MaterialTheme.colors.background,
                focusedBorderColor = MaterialTheme.colors.primary,
                unfocusedBorderColor = MaterialTheme.colors.primaryVariant,
            ),
            modifier = Modifier
                .border(2.dp, MaterialTheme.colors.primary)
                .focusRequester(focusRequester)
                .onFocusChanged {
                    println("on Focus Changed")
                    keyboardController?.hide()
//                    if (focus.value != it.isFocused) {
//                        focus.value = it.isFocused
//                        if (!it.isFocused) {
//                        }
//                    }
                },
//            keyboardActions = KeyboardActions(onDone = {
//                println("onDone event")
//                focusManager.clearFocus()
//            }),
        )
        Button(
            onClick = {
                focusRequester.requestFocus()
                keyboardController?.hide()
            }
        ) {
            Text("Click to give the focus")
        }
        Button(
            onClick = { }
        ) {
            Text("Empty click")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "$text2")
        Button(
            onClick = {
                /* going to second screen with the value of text */
                navController.navigate("second/$resECEC")
            }
        ) {
            Text(text = "Second Screen")
        }
        Greeting()
    }
}

@Composable
fun SecondScreen(navController: NavController, content: ECECModel?) {
    require(content != null)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                /* going back to the main screen */
                navController.navigateUp()
            }
        ) {
            Text(text = "Go back")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(Modifier.fillMaxSize()) {
            Text("Look at this CustomView!")
            CustomView(content)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun CustomView(content: ECECModel?) {
    val state = remember { mutableStateOf(0) }
    // Compose Button
    androidx.compose.material.Button(onClick = { state.value++ }) {
        Text("MyComposeButton")
    }

    // widget.Button
    AndroidView(factory = { ctx ->
        android.widget.Button(ctx).apply {
            text = "MyAndroidButton"
            layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            setOnClickListener {
                state.value++
            }
        }
    }, modifier = Modifier.padding(8.dp))

    // widget.TextView
    AndroidView(factory = { ctx ->
        TextView(ctx).apply {
            layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        }
    }, update = {
        // Update TextView with the current state value
        it.text = "You have clicked the buttons: " + state.value.toString() + " times"
    })

//    AndroidView(factory = { ctx ->
//        TextView(ctx).apply {
//            layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
//        }
//    }, update = {
//        // Update TextView with the current state value
//        it.text = content?.toString()
//    })
}

@Composable
fun Greeting() {
    var timeCall by remember { mutableStateOf("Temps du Call : ") }
    var timeElapsed by remember { mutableStateOf("elapsed : ") }

    val threadAuthenticate = Thread {
        try {
            ApiHelper.authenticate()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    val threadECEC = Thread {
        try {
            val elapsed = measureTimeMillis {
                println("Measuring time via measureTimeMillis")
                ApiHelper.getECEC()
            }
            timeElapsed = "elapsed : $elapsed"
            timeCall = "Temps du Call : ${ApiHelper.getTimeToCall()}"
//        textTest = "$elapsed"
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
//        Text(text = "Bonjour $name!")
        Button(onClick = {
            threadAuthenticate.start()
        }) {
            Text("TOKEN")
        }
        Button(onClick = {
            threadECEC.start()
        }) {
            Text("get ECEC")
        }
        Text(text = "Description : ${ApiHelper.description}")
        Text(text = timeCall)
        Text(text = timeElapsed)
    }
}