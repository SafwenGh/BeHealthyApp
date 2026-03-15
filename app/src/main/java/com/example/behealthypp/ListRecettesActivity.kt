package com.example.behealthypp  // Adaptez à votre package

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.IOException

class ListRecettesActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_recettes)

        val listView = findViewById<ListView>(R.id.listViewRecettes)

        // Initialiser la base de données
        dbHelper = DBHelper(this)
        db = dbHelper.writableDatabase

        // Lire les recettes depuis le fichier JSON
        val recettesData = lireRecettesDepuisJSON()

        // Afficher uniquement les noms dans la ListView
        val nomsRecettes = recettesData.map { it["nom"] }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nomsRecettes)
        listView.adapter = adapter

        // Gérer le clic sur un élément
        listView.setOnItemClickListener { _, _, position, _ ->
            val recetteSelectionnee = recettesData[position]
            val nom = recetteSelectionnee["nom"] ?: ""
            val ingredients = recetteSelectionnee["ingredients"] ?: ""

            Toast.makeText(this, "Ajout de : $nom", Toast.LENGTH_SHORT).show()

            // Sauvegarder dans SQLite
            val values = ContentValues().apply {
                put("nom", nom)
                put("ingredients", ingredients)
            }

            db.insert("recettes", null, values)
        }
    }

    private fun lireRecettesDepuisJSON(): ArrayList<Map<String, String>> {
        val liste = ArrayList<Map<String, String>>()

        try {
            val inputStream = assets.open("recettes.json")
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val jsonObject = JSONObject(jsonString)
            val jsonArray = jsonObject.getJSONArray("recettes")

            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                val map = mapOf(
                    "nom" to item.getString("nom"),
                    "ingredients" to item.getString("ingredients")
                )
                liste.add(map)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Erreur de lecture du fichier JSON", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Erreur: ${e.message}", Toast.LENGTH_SHORT).show()
        }

        return liste
    }

    override fun onDestroy() {
        db.close()
        super.onDestroy()
    }
}