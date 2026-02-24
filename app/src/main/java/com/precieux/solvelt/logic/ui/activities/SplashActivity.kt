package com.precieux.solvelt.logic.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import android.widget.ScrollView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import java.io.StringWriter
import java.io.PrintWriter

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            // Configure le handler d'exceptions non capturées
            Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
                runOnUiThread {
                    showError(throwable)
                }
            }
            
            // Ton code normal
            val textView = TextView(this).apply {
                text = "SolVelt Logic\nChargement..."
                textSize = 24f
                gravity = android.view.Gravity.CENTER
            }
            
            setContentView(textView)
            
            // Redirige vers MainActivity après 2 secondes
            Handler(Looper.getMainLooper()).postDelayed({
                try {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } catch (e: Exception) {
                    showError(e)
                }
            }, 2000)
            
        } catch (e: Exception) {
            showError(e)
        }
    }
    
    private fun showError(throwable: Throwable) {
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        throwable.printStackTrace(pw)
        val stackTrace = sw.toString()
        
        val scrollView = ScrollView(this)
        val linearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 50, 50, 50)
        }
        
        val titleView = TextView(this).apply {
            text = "⚠️ CRASH DÉTECTÉ ⚠️"
            textSize = 24f
            setTextColor(0xFFFF0000.toInt())
        }
        linearLayout.addView(titleView)
        
        val messageView = TextView(this).apply {
            text = "Message: ${throwable.message}"
            textSize = 18f
        }
        linearLayout.addView(messageView)
        
        val stackView = TextView(this).apply {
            text = stackTrace
            textSize = 12f
        }
        linearLayout.addView(stackView)
        
        scrollView.addView(linearLayout)
        setContentView(scrollView)
    }
}
