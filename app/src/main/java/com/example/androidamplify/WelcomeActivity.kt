package com.example.androidamplify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class WelcomeActivity : AppCompatActivity() {
    private lateinit var btnLogOut: Button

    private val cognitoHelper by lazy {
        CognitoHelper()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        btnLogOut = findViewById(R.id.btn_confirm)

        btnLogOut.setOnClickListener {
            cognitoHelper.logOut { isComplete ->
                when(isComplete) {
                    true -> this.finish()
                    else -> launchToast("Thank you, session closed!")
                }
            }
        }
    }

    private fun launchToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}