package com.precieux.solvelt.logic

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val textView = TextView(this).apply {
            text = "TEST - L'application fonctionne correctement !"
            textSize = 24f
            setPadding(50, 50, 50, 50)
        }
        
        setContentView(textView)
    }
}
