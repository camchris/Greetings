package com.example.greetings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greetings.ui.theme.GreetingsTheme
import java.time.LocalDateTime
import java.time.LocalTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingsTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Screen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun Screen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Greet(
            modifier = Modifier
                .padding(innerPadding) // Applies the inner padding provided by Scaffold
                .padding(20.dp)
        )
    }

}

@Composable
fun Greet(modifier: Modifier = Modifier) {

    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var textState = remember { mutableStateOf("") }
        val onTextChange = { text : String ->
            textState.value = text
        }
        TextField(
            value = textState.value,
            onValueChange = onTextChange
        )

        var current by remember { mutableStateOf("") }
        var msg = ""

        Button(
            onClick = {
                val currentTime = LocalTime.now()
                if (currentTime.isAfter(LocalTime.of(22, 0))) {
                    msg = "Hope you sleep well!"
                } else if (currentTime.isAfter(LocalTime.of(17, 0))){
                    msg = "It's time to relax :)"
                } else if (currentTime.isAfter(LocalTime.of(14, 0))) {
                    msg = "Hang in there, only a few hours left in the work day!"
                } else if (currentTime.isAfter(LocalTime.of(12, 0))) {
                    msg = "What are you having for lunch?"
                } else if (currentTime.isAfter(LocalTime.of(8, 0))) {
                    msg = "Have a good day!"
                } else if (currentTime.isAfter(LocalTime.of(14, 0))) {
                    msg = "You're up early."
                } else {
                    msg = "Go to bed."
                }
                current = "Hello " + textState.value + "! " + msg
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(255, 128, 0), // Background color of the button
                contentColor = Color.White // Color of the button text
            ),

            ) {
            Text(
                text = "Enter Name",
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                )
            )
        }
        Text(
            text = current,
            modifier = modifier,
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp,
                color = Color(0, 51, 102),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )



    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreetingsTheme {
        Screen()
    }
}