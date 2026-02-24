package com.precieux.solvelt.logic.ui.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val textView = TextView(this)
        textView.text = "SI TU VOIS ÇA, L'APP FONCTIONNE"
        textView.textSize = 24f
        
        setContentView(textView)
    }
}
