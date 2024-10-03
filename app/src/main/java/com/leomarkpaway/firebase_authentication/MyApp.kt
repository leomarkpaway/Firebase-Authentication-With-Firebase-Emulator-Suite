package com.leomarkpaway.firebase_authentication

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()
        auth.useEmulator("10.0.2.2", 9099)
    }

    companion object {
        lateinit var auth: FirebaseAuth
    }

}