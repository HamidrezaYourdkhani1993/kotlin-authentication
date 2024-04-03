package com.example.firebase

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*

class profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        enableEdgeToEdge()

        // Retrieve email from intent extra
        val email = intent.getStringExtra("email")

        // Set email TextView
        val emailTextView = findViewById<TextView>(R.id.emailTextView)
        emailTextView.text = "Email: $email"

        // Get current user
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            // Retrieve creation timestamp from user metadata
            val metadata = it.metadata
            if (metadata != null) {
                val creationTimestamp = metadata.creationTimestamp

                // Convert timestamp to date string
                val creationDate = convertTimestampToDate(creationTimestamp)

                // Set creation date TextView
                val creationDateTextView = findViewById<TextView>(R.id.creationDateTextView)
                creationDateTextView.text = "Account created on: $creationDate"
            }
        }

        // Apply edge-to-edge navigation
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun convertTimestampToDate(timestamp: Long): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(Date(timestamp))
    }

    private fun enableEdgeToEdge() {

    }
}
