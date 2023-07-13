package com.example.androidamplify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var edtUsername: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnSignUp: Button

    private lateinit var edtCode: EditText
    private lateinit var btnConfirmCode: Button

    private lateinit var edtLoginUser: EditText
    private lateinit var edtLoginPass: EditText
    private lateinit var btnLogIn: Button

    private val cognitoHelper by lazy {
        CognitoHelper()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtUsername = findViewById(R.id.edt_username)
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnSignUp = findViewById(R.id.btn_signUp)

        edtCode = findViewById(R.id.edt_code)
        btnConfirmCode = findViewById(R.id.btn_confirm)

        edtLoginUser = findViewById(R.id.edt_login_username)
        edtLoginPass = findViewById(R.id.edt_login_password)
        btnLogIn = findViewById(R.id.btn_login)

        cognitoHelper.validateSession { hasSession ->
            if (hasSession) {
                goToWelcome()
            } else {
                handleRegistrationOrLogin()
            }
        }
    }

    private fun handleRegistrationOrLogin() {
        btnSignUp.setOnClickListener {
            cognitoHelper.registerUser(
                username = edtUsername.text.toString(),
                password = edtPassword.text.toString(),
                email = edtEmail.text.toString(),
                ::handleRegistration
            )
        }
        btnLogIn.setOnClickListener {
            cognitoHelper.login(
                username = edtUsername.text.toString(),
                password = edtPassword.text.toString(),
                ::handleLogin
            )
        }
    }

    private fun handleRegistration(isComplete: Boolean) {
        runOnUiThread {
            if (isComplete) {
                edtUsername.visibility = View.GONE
                edtEmail.visibility = View.GONE
                edtPassword.visibility = View.GONE
                btnSignUp.visibility = View.GONE
                edtCode.visibility = View.VISIBLE
                btnConfirmCode.visibility = View.VISIBLE
                edtLoginUser.visibility = View.GONE
                edtLoginPass.visibility = View.GONE
                btnLogIn.visibility = View.GONE

                btnConfirmCode.setOnClickListener {
                    cognitoHelper.confirmUser(
                        username = edtUsername.text.toString(),
                        code = edtCode.text.toString(),
                        ::handleConfirmation
                    )
                }
            } else {
                launchToast("Registration failed! Try again")
            }
        }
    }

    private fun handleConfirmation(isComplete: Boolean?) {
        runOnUiThread {
            when (isComplete) {
                true -> goToWelcome()
                else -> launchToast("An error appears, try again")
            }
        }
    }

    private fun handleLogin(isComplete: Boolean?) {
        runOnUiThread {
            when (isComplete) {
                true -> goToWelcome()
                else -> launchToast("An error appears, try again")
            }
        }
    }

    private fun goToWelcome() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
    }

    private fun launchToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}