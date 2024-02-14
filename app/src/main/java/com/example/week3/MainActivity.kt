package com.example.week3

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Switch
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week3.ui.theme.Week3Theme
import java.util.Locale


private lateinit var flagImage: ImageView
private lateinit var languageSwitch: Switch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flagImage = findViewById(R.id.flag_image)
        languageSwitch = findViewById(R.id.language_switch)
        lateinit var emailEditText: EditText
        lateinit var passwordEditText: EditText
        lateinit var submitButton: Button

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


        fun onCreate(savedInstanceState: Bundle?) {
            onCreate(savedInstanceState)
            setContentView(R.layout.activity_login) // Replace with your layout resource ID

            emailEditText = findViewById(R.id.email_editText)
            passwordEditText = findViewById(R.id.password_editText)
            submitButton = findViewById(R.id.submit_button)

            submitButton.setOnClickListener {
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                // Perform login logic here (e.g., check credentials, display error messages)
                // For now, we'll just show a toast message
                Toast.makeText(this, "Email: $email, Password: $password", Toast.LENGTH_SHORT).show()
            }

            setContentView(R.layout.activity_login) // Replace with your layout resource ID

            emailEditText = findViewById(R.id.email_editText)
            passwordEditText = findViewById(R.id.password_editText)
            submitButton = findViewById(R.id.submit_button)

            // Pre-fill email address from SharedPreferences
            val savedEmail = sharedPreferences.getString("LoginName", "")
            emailEditText.setText(savedEmail)

            submitButton.setOnClickListener {
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                // Perform login logic here (e.g., check credentials, display error messages)
                // For now, we'll just show a toast message
                Toast.makeText(this, "Email: $email, Password: $password", Toast.LENGTH_SHORT).show()

                // Save the new email address to SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("LoginName", email)
                editor.apply() // Make sure to use apply() for asynchronous writing
            }
        }
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

fun onStart(){
    Log.d("My app", "onStart called")
}

fun onResume(){
    Log.d("My App", "onResume called")
}

fun onPause(){
    Log.d("My App", "onPause called")
}

fun onStop(){
    Log.d("My App", "onStop called")
}

fun onDestroy(){
    Log.d("My App", "onDestroy called")
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Week3Theme {
        Greeting("Android")
    }
}