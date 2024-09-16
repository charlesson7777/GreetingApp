package com.example.greetingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.material3.Surface
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.greetingapp.ui.theme.GreetingAppTheme
import androidx.compose.ui.unit.sp
import java.util.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    var name by remember{ mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        TextField(
            value = name,
            onValueChange = {name = it},
            label = {Text("Please type your name")},
            modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp)
        )

        Button(
            onClick = {
                if (name.isNotBlank()) {
                    message = generatedMessage(name)
                } else {
                    message = "Please type your name."
                }
            },
            modifier = Modifier.padding(top = 7.dp)
                .width(145.dp)
                .height(50.dp))
        {
            Text("Greeting")
        }

        if(message.isNotEmpty()){
            Text(
                text = message,
                fontSize = 26.sp,
                modifier = Modifier.padding(top = 18.dp)
            )
        }

    }
}

fun generatedMessage(name: String): String {
    val recentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val greetingTime = when (recentHour){
        in 5..11 -> "Good Morning, "
        in 12..17 -> "Good Afternoon, "
        in 18..20 -> "Good Evening, "
        else -> "Good Night"
    }
    return "$greetingTime, $name!!"

    }


