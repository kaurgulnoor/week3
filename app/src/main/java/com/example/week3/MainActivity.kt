package com.example.week3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.benchmark.perfetto.ExperimentalPerfettoTraceProcessorApi
import androidx.benchmark.perfetto.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week3.ui.theme.Week3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@OptIn(ExperimentalPerfettoTraceProcessorApi::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.padding(horizontal = 17.5.dp) // Half of spacing added to each side
    ) {
        Text(text = "Button1")
    }
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.padding(horizontal = 17.5.dp) // Half of spacing added to each side
    ) {
        Text(text = "Button2")
    }
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.padding(horizontal = 17.5.dp) // Half of spacing added to each side
    ) {
        Text(text = "Button3")
    }
}


fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    flagImage = findViewById(R.id.flag_image)
    languageSwitch = findViewById(R.id.language_switch)

    languageSwitch.setOnCheckedChangeListener { _, isChecked ->
        if (isChecked) {
            // Set flag image based on current device language
            val currentLanguage = Locale.getDefault().language
            val flagResource = resources.getIdentifier("flag_$currentLanguage", "drawable", packageName)
            if (flagResource != 0) {
                flagImage.setImageResource(flagResource)
            }
        } else {
            // Set default flag image (e.g., English)
            flagImage.setImageResource(R.drawable.flag_en)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Week3Theme {
        Greeting("Android")
    }
}