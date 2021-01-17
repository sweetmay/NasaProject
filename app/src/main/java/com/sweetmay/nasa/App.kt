package com.sweetmay.nasa

import android.app.Application
class App: Application() {

    companion object{
        val SETTINGS = "prefs"
        val THEME_KEY = "themedark"
        val BASE_URL = "https://api.nasa.gov"
        val BASE_URL_EPIC = "https://epic.gsfc.nasa.gov/archive/natural/"
        val API_KEY: String = BuildConfig.api_key
        lateinit var instance: App
        private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }
}