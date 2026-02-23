package com.precieux.solvelt.logic

import android.app.Application
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

    override fun onCreate() {
        super.onCreate()
        
        // Initialize default night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        
        // Initialize database with default data
        initializeDatabase()
    }
    
    private fun initializeDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                SolVeltDatabase.getDatabase(this@SolVeltApplication)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}