package com.precieux.solvelt.logic.ui.activities

import android.os.Bundle
import android.widget.TextView
import android.graphics.Color
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Forcer un thème clair avec fond blanc
        val textView = TextView(this).apply {
            text = "MAIN ACTIVITY - VISIBLE !"
            textSize = 24f
            setTextColor(Color.BLACK)
            setBackgroundColor(Color.WHITE)
            gravity = Gravity.CENTER
        }
        
        setContentView(textView)
    }
}
