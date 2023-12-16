package com.cookandroid.masterkey


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Bus1 : AppCompatActivity() {
        private lateinit var btnLeft: ImageView
        private lateinit var tvCenter: TextView
        private lateinit var btnRight: ImageView
        private lateinit var bus1: Button
        private lateinit var bus2: Button
        private lateinit var bus3: Button
        private lateinit var korbus: Button
        private lateinit var engbus: Button
        private lateinit var chibus: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus1)

        val btnLeftImageView = findViewById<ImageView>(R.id.btnLeft)
        val btnRightImageView = findViewById<ImageView>(R.id.btnRight)
        btnLeft = findViewById(R.id.btnLeft)
        tvCenter = findViewById(R.id.tvCenter)
        btnRight = findViewById(R.id.btnRight)
        bus1 = findViewById(R.id.bus1)
        bus2 = findViewById(R.id.bus2)
        bus3 = findViewById(R.id.bus3)
        korbus = findViewById(R.id.korbus)
        engbus = findViewById(R.id.engbus)
        chibus = findViewById(R.id.chibus)

        // Set OnClickListener for the left button
        btnLeftImageView.setOnClickListener {
            // Handle the left button click
            navigateToBusActivity()
        }

        // Set OnClickListener for the right button
        btnRightImageView.setOnClickListener {
            // Handle the right button click
            navigateToMainActivity()
        }

        bus1.setOnClickListener {
            // Define the action you want to take when bus1 is clicked
            // For example, you can start a new activity
            val intent = Intent(this, Bus2::class.java)
            startActivity(intent)

        }

        // ... (rest of your code)
    }

    // Function to navigate to the BusActivity
    private fun navigateToBusActivity() {
        // Create an Intent to start the BusActivity
        val intent = Intent(this, Bus1::class.java)

        // Start the BusActivity
        startActivity(intent)
    }

    // Function to navigate to the MainActivity
    private fun navigateToMainActivity() {
        // Create an Intent to start the MainActivity
        val intent = Intent(this, MainActivity::class.java)

        // Start the MainActivity
        startActivity(intent)
    }
}






