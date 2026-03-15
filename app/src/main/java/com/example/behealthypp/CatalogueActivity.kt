package com.example.behealthypp  // Adaptez à votre package

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CatalogueActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogue)

        val btn1 = findViewById<Button>(R.id.btnProduit1)
        val btn2 = findViewById<Button>(R.id.btnProduit2)
        val btn3 = findViewById<Button>(R.id.btnProduit3)

        btn1.setOnClickListener {
            Toast.makeText(this, "Produit 1 ajouté au panier √π", Toast.LENGTH_SHORT).show()
        }

        btn2.setOnClickListener {
            Toast.makeText(this, "Produit 2 ajouté au panier √π", Toast.LENGTH_SHORT).show()
        }

        btn3.setOnClickListener {
            Toast.makeText(this, "Produit 3 ajouté au panier √π", Toast.LENGTH_SHORT).show()
        }
    }
}