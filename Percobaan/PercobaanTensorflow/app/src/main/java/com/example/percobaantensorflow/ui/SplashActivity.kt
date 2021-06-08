package com.example.percobaantensorflow.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.percobaantensorflow.R

class SplashActivity : AppCompatActivity() {
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val second : Long = 5000
        supportActionBar?.hide()

        handler = Handler()
        handler.postDelayed({
            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
            finish()

        }, second)
    }
}