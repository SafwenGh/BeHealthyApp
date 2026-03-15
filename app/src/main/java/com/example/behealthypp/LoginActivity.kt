package com.example.behealthypp  // Adaptez à votre package

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etClientName = findViewById<EditText>(R.id.etClientName)
        val cbRemember = findViewById<CheckBox>(R.id.cbRemember)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // Initialiser SharedPreferences
        sharedPref = getSharedPreferences("RecettesPrefs", Context.MODE_PRIVATE)

        // Charger les préférences sauvegardées
        val savedName = sharedPref.getString("CLIENT", "")
        val savedRemember = sharedPref.getBoolean("REMEMBER", false)

        etClientName.setText(savedName)
        cbRemember.isChecked = savedRemember

        btnLogin.setOnClickListener {
            val clientName = etClientName.text.toString()

            if (clientName.isNotEmpty()) {
                // Sauvegarder les préférences
                val editor = sharedPref.edit()

                if (cbRemember.isChecked) {
                    editor.putString("CLIENT", clientName)
                    editor.putBoolean("REMEMBER", true)
                } else {
                    editor.remove("CLIENT")
                    editor.putBoolean("REMEMBER", false)
                }
                editor.apply()

                // Naviguer vers la liste des recettes
                Toast.makeText(this, "Bienvenue $clientName", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ListRecettesActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Veuillez entrer votre nom", Toast.LENGTH_SHORT).show()
            }
        }
    }
}