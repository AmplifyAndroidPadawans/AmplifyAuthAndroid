package com.example.androidamplify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var edtUsername: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnSignIn: Button

    private val cognitoHelper by lazy {
        CognitoHelper()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtUsername = findViewById(R.id.edt_username)
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnSignIn = findViewById(R.id.btn_signIn)

        btnSignIn.setOnClickListener {
            cognitoHelper.registerUser(
                username = edtUsername.text.toString(),
                password = edtPassword.text.toString(),
                email = edtEmail.text.toString(),
            )
        }
    }
}