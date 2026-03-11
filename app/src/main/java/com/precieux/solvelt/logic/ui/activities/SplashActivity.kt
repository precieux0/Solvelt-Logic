package com.precieux.solvelt.logic.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.os.Looper
import android.util.Log

class SplashActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "SplashActivity"
        private const val SPLASH_DELAY = 3000L // 3 secondes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            
            // Utiliser le layout XML
            setContentView(R.layout.activity_splash_new)
            
            Log.d(TAG, "SplashActivity créée avec succès")
            
            // Lancer MainActivity après le délai
            Handler(Looper.getMainLooper()).postDelayed({
                try {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    Log.d(TAG, "Transition vers MainActivity")
                } catch (e: Exception) {
                    Log.e(TAG, "Erreur lors du lancement de MainActivity", e)
                    finish()
                }
            }, SPLASH_DELAY)
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur fatale dans SplashActivity", e)
            // En cas d'erreur, lancer directement MainActivity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
