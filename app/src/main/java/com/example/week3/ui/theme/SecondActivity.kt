package com.example.week3.ui.theme

import android.content.Context.MODE_PRIVATE
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    private lateinit var emailTextView: TextView
    private lateinit var phoneEditText: EditText
    private lateinit var imageView: ImageView
    private lateinit var callButton: Button
    private lateinit var changePictureButton: Button

    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_STORAGE_PERMISSION = 2
    private val sharedPreferences by lazy { getSharedPreferences("MyData", MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second) // Replace with your layout resource ID

        emailTextView = findViewById(R.id.email_text_view)
        phoneEditText = findViewById(R.id.phone_edit_text)
        imageView = findViewById(R.id.image_view)
        callButton = findViewById(R.id.call_button)
        changePictureButton = findViewById(R.id.change_picture_button)

        // Receive email from intent
        val receivedEmail = intent.getStringExtra("userEmail")
        emailTextView.text = receivedEmail

        // Load phone number from SharedPreferences
        val savedPhone = sharedPreferences.getString("PhoneNumber", "")
        phoneEditText.setText(savedPhone)

        // Load image from file, handle errors gracefully
        try {
            val savedImage = loadBitmapFromFile(
                sharedPreferences.getString("ImagePath", "") ?: ""
            )
            imageView.setImageBitmap(savedImage)
        } catch (e: Exception) {
            Log.e("SecondActivity", "Error loading image: $e")
            imageView.setImageResource(R.drawable.default_image) // Replace with default image placeholder
        }

        callButton.setOnClickListener {
            val phoneNumber = phoneEditText.text.toString()

            // Validate phone number format (optional)
            // ...

            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)

            // Save new phone number to SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("PhoneNumber", phoneNumber)
            editor.apply()
        }

        changePictureButton.setOnClickListener {
            // Request storage permission if needed
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_STORAGE_PERMISSION
                )
            } else {
                // Launch camera intent
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (intent.resolveActivity(packageManager) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_STORAGE_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    changePictureButton.performClick() // Open camera after permission granted
                } else {
                    // Handle permission denial gracefully (e.g., explain importance, offer settings link)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)

            // Save the new image to disk (implement your logic here)
            val savedPath = saveBitmapToFile(imageBitmap)

            // Update SharedPreferences with the new image path
            val editor = sharedPreferences.edit()
            editor.putString("ImagePath", savedPath)
            editor.apply()
        }
    }

    // Implement functions for loading/saving bitmaps to files based on your storage approach
    private fun loadBitmapFromFile(imagePath: String): Bitmap? {
        // ... (Implementation to read bitmap from file)
    }

    private fun saveBitmapToFile(bitmap: Bitmap): String {
        // ... (Implementation to save bitmap to file and return path)
    }
}

open class AppCompatActivity {

}
