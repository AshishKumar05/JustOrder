package com.example.justorder

import android.app.Application
import android.content.res.Configuration
import com.example.justorder.data.APIManager
import com.example.justorder.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JustOrderApplication : Application() {

    var gson : Gson ?= null
    var retrofit : Retrofit ? =null

    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    override fun  onCreate() {
        super.onCreate()
        // Required initialization logic here!
    }
    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    override fun onConfigurationChanged ( newConfig : Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    override fun onLowMemory() {
        super.onLowMemory()
    }

    fun getMyGson(): Gson? {
        if (gson == null) {
            gson = GsonBuilder()
                .setLenient()
                .create()
        }
        return gson
    }

    fun getMyRetrofit(): Retrofit? {
        retrofit = Retrofit.Builder()
            .baseUrl(getBaseURL())
            .addConverterFactory(GsonConverterFactory.create(getMyGson()))
            .build()
        return retrofit
    }

    fun getBaseURL(): String? {
        return Constants.BASE_URL
    }

    fun getAPIManager(): APIManager? {
        if (retrofit == null) {
            retrofit = getMyRetrofit()
        }
        return retrofit?.create(APIManager::class.java)
    }
}