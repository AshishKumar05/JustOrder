package com.example.justorder

import android.app.Application
import com.example.justorder.data.APIManager
import com.example.justorder.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JustOrderApplication : Application() {

    var gson : Gson ?= null
    var retrofit : Retrofit ? =null

    override fun  onCreate() {
        super.onCreate()
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