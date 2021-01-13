package com.sweetmay.nasa

import android.app.Application
class App: Application() {

    companion object{
        val base_url = "https://api.nasa.gov"
        val api_key: String = BuildConfig.api_key
        lateinit var instance: App
        private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }
}