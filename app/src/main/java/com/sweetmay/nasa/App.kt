package com.sweetmay.nasa

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.CoilUtils
import okhttp3.OkHttpClient

class App: Application(), ImageLoaderFactory {

    companion object{
        val ROVER_FRAGMENT_KEY = "fragment_key"
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

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(applicationContext)
                .crossfade(true)
                .okHttpClient {
                    OkHttpClient.Builder()
                            .cache(CoilUtils.createDefaultCache(applicationContext))
                            .build()
                }
                .build()
    }
}