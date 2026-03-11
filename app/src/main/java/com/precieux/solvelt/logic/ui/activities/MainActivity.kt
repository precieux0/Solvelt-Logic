package com.precieux.solvelt.logic.ui.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            
            // Utiliser le layout XML amélioré
            setContentView(R.layout.activity_main_new)
            
            Log.d(TAG, "MainActivity créée avec succès")
            
            // Initialiser la navigation
            try {
                val navHostFragment = supportFragmentManager
                    .findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
                
                if (navHostFragment != null) {
                    navController = navHostFragment.navController
                    bottomNav = findViewById(R.id.bottom_nav)
                    NavigationUI.setupWithNavController(bottomNav, navController)
                    Log.d(TAG, "Navigation initialisée avec succès")
                } else {
                    Log.w(TAG, "NavHostFragment non trouvé, affichage d'une interface simple")
                    showFallbackUI()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Erreur lors de l'initialisation de la navigation", e)
                showFallbackUI()
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur fatale dans MainActivity", e)
            showFallbackUI()
        }
    }
    
    private fun showFallbackUI() {
        try {
            val textView = TextView(this).apply {
                text = "Bienvenue dans SolVelt Logic!\n\nLe chargement de l'interface complète est en cours..."
                textSize = 18f
                setPadding(50, 50, 50, 50)
                gravity = android.view.Gravity.CENTER
            }
            setContentView(textView)
            Log.d(TAG, "Interface de secours affichée")
        } catch (e: Exception) {
            Log.e(TAG, "Erreur lors de l'affichage de l'interface de secours", e)
        }
    }
}
