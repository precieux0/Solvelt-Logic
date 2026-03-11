package com.precieux.solvelt.logic
import android.content.Context

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.precieux.solvelt.logic.data.database.SolVeltDatabase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@HiltAndroidApp
class SolVeltApplication : Application() {
    companion object {
        private const val TAG = "SolVeltApplication"
    }

    override fun onCreate() {
        try {
            super.onCreate()
            
            Log.d(TAG, "Initialisation de SolVeltApplication")
            
            // Initialize default night mode
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            
            Log.d(TAG, "Mode nuit système activé")
            
            // Initialize database with default data (optionnel)
            // initializeDatabase()
            
            Log.d(TAG, "SolVeltApplication initialisée avec succès")
            
        } catch (e: Exception) {
            Log.e(TAG, "Erreur fatale lors de l'initialisation de l'application", e)
            e.printStackTrace()
        }
    }
    
    private fun initializeDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d(TAG, "Initialisation de la base de données...")
                SolVeltDatabase.getDatabase(this@SolVeltApplication)
                Log.d(TAG, "Base de données initialisée")
            } catch (e: Exception) {
                Log.e(TAG, "Erreur lors de l'initialisation de la base de données", e)
                e.printStackTrace()
            }
        }
    }
}