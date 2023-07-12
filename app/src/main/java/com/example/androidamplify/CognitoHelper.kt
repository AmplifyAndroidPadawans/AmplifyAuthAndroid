package com.example.androidamplify

import android.util.Log
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.core.Amplify

class CognitoHelper {

    fun registerUser(
        username: String,
        password: String,
        email: String,
    ) {
        val options = AuthSignUpOptions.builder()
            .userAttribute(AuthUserAttributeKey.email(), email)
            .build()
        Amplify.Auth.signUp(username, password, options,
            {
                Log.i("CognitoHelper", "Sign up succeeded: $it")
            },
            {
                Log.e ("CognitoHelper", "Sign up failed", it)
            }
        )
    }
}