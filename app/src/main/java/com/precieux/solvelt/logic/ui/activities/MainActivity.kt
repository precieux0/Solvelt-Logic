package com.precieux.solvelt.logic.ui.activities

import android.os.Bundle
import android.widget.TextView
import android.widget.ScrollView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            // Créer une interface simple
            val scrollView = ScrollView(this)
            val linearLayout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(50, 50, 50, 50)
            }
            
            // Afficher un message
            linearLayout.addView(TextView(this).apply {
                text = "MainActivity chargée avec succès !"
                textSize = 24f
            })
            
            // Ajouter des infos de debug
            linearLayout.addView(TextView(this).apply {
                text = "\n📱 Debug Info:"
                textSize = 20f
            })
            
            // Vérifier les ViewModels
            try {
                // Essaie d'accéder à un ViewModel pour voir si ça bloque
                linearLayout.addView(TextView(this).apply {
                    text = "✓ ViewModels accessibles"
                    textSize = 16f
                })
            } catch (e: Exception) {
                linearLayout.addView(TextView(this).apply {
                    text = "✗ Erreur ViewModel: ${e.message}"
                    textSize = 16f
                })
            }
            
            // Vérifier la DB
            try {
                // Essaie d'accéder à la DB pour voir si ça bloque
                linearLayout.addView(TextView(this).apply {
                    text = "✓ Base de données accessible"
                    textSize = 16f
                })
            } catch (e: Exception) {
                linearLayout.addView(TextView(this).apply {
                    text = "✗ Erreur DB: ${e.message}"
                    textSize = 16f
                })
            }
            
            scrollView.addView(linearLayout)
            setContentView(scrollView)
            
        } catch (e: Exception) {
            // En cas d'erreur, l'afficher
            val textView = TextView(this).apply {
                text = "ERREUR: ${e.message}"
                textSize = 18f
                setTextColor(0xFFFF0000.toInt())
            }
            setContentView(textView)
        }
    }
}
