package com.precieux.solvelt.logic.utils

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.precieux.solvelt.logic.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class LanguageManager(private val context: Context) {

    companion object {
        val LANGUAGE_KEY = stringPreferencesKey("language")
        const val LANGUAGE_ENGLISH = "en"
        const val LANGUAGE_FRENCH = "fr"
        const val LANGUAGE_SYSTEM = "system"
    }

    val currentLanguage: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[LANGUAGE_KEY] ?: LANGUAGE_SYSTEM
        }

    suspend fun setLanguage(languageCode: String) {
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE_KEY] = languageCode
        }
        updateLocale(languageCode)
    }

    fun updateLocale(languageCode: String) {
        val locale = when (languageCode) {
            LANGUAGE_ENGLISH -> Locale.ENGLISH
            LANGUAGE_FRENCH -> Locale.FRENCH
            else -> Locale.getDefault()
        }

        Locale.setDefault(locale)
        
        val config = Configuration(context.resources.configuration)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale)
        } else {
            config.locale = locale
        }
        
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    fun getSystemLanguage(): String {
        return when (Locale.getDefault().language) {
            LANGUAGE_FRENCH -> LANGUAGE_FRENCH
            else -> LANGUAGE_ENGLISH
        }
    }

    fun getLanguageName(languageCode: String): String {
        return when (languageCode) {
            LANGUAGE_ENGLISH -> "English"
            LANGUAGE_FRENCH -> "Français"
            LANGUAGE_SYSTEM -> "System Default"
            else -> "English"
        }
    }
}