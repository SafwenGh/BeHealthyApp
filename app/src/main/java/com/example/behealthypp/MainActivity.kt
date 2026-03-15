package com.example.behealthypp  // Make sure this matches!

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.behealthypp.R

// The R import is automatic, don't add it manually unless needed

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // R should be recognized here

        val btnNext = findViewById<Button>(R.id.btnNext)  // And here

        btnNext.setOnClickListener {
            Toast.makeText(this, "Bouton cliqué!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}