package com.example.androidamplify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.os.Handler

private val cognitoHelper by lazy {
    CognitoHelper()
}

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed ({
            cognitoHelper.validateSession { hasSession ->
                if (hasSession) {
                    goToWelcome()
                } else {
                    goToRegistration()
                }
            }
        }, 2000)
    }
    private fun goToWelcome() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
    }
    private fun goToRegistration() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
