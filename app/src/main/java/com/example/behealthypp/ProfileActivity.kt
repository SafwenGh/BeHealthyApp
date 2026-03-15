package com.example.behealthypp  // Adaptez à votre package

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btnCatalogue = findViewById<Button>(R.id.btnCatalogue)
        btnCatalogue.setOnClickListener {
            val intent = Intent(this, CatalogueActivity::class.java)
            startActivity(intent)
        }
    }
}