package com.precieux.solvelt.logic.ui.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val textView = TextView(this).apply {
            text = "Main Activity - Application fonctionnelle !"
            textSize = 24f
            setPadding(50, 50, 50, 50)
            gravity = android.view.Gravity.CENTER
        }
        
        setContentView(textView)
    }
}
