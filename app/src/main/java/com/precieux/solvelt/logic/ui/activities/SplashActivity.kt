package com.precieux.solvelt.logic.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.appCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Va directement à MainActivity
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
