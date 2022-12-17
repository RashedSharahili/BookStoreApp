package com.example.bookstore

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.annotation.RequiresApi

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var scaleText : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
            supportActionBar?.hide()
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
            supportActionBar?.hide()
        }
        setContentView(R.layout.activity_splash_screen)

        // Animation
        scaleText = AnimationUtils.loadAnimation(this, R.anim.scale_animation)

        val appNameSpalshTV = findViewById<TextView>(R.id.app_name_splash)

        appNameSpalshTV.startAnimation(scaleText)

        Handler(Looper.getMainLooper()).postDelayed({
            kotlin.run {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 2400)
    }
}