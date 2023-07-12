package com.example.androidamplify

import android.app.Application
import android.util.Log

import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin

import com.amplifyframework.core.Amplify


class AmplifyAndroidApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.configure(applicationContext)
            Log.i("Tutorial", "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e("Tutorial", "Could not initialize Amplify", error)
        }
    }
}