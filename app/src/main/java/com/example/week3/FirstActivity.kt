package com.example.week3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var submitButton: Button

    private val sharedPreferences by lazy { getSharedPreferences("MyData", MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // Replace with your layout resource ID

        emailEditText = findViewById(R.id.email_editText)
        passwordEditText = findViewById(R.id.password_editText)
        submitButton = findViewById(R.id.submit_button)

        // ... (previous code for pre-filling email and login logic)

        submitButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // ... (previous code for handling login)

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("userEmail", email)
            startActivity(intent)
        }
    }

    private fun setContentView(activityLogin: Int) {

    }
}
