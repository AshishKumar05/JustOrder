package com.example.justorder.data

import com.example.justorder.data.APIManager
import com.example.justorder.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    var gson : Gson?= null
    var retrofit : Retrofit? =null

    fun getMyGson(): Gson? {
        if (gson == null) {
            gson = GsonBuilder()
                    .setLenient()
                    .create()
        }
        return gson
    }

    fun getMyRetrofit(): Retrofit {
      return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(getMyGson()))
                .build()

    }

    fun getBaseURL(): String? {
        return "http://192.168.183.95:8080/"
    }



    fun getAPIManager(): APIManager? {
        if (retrofit == null) {
            retrofit = getMyRetrofit()
        }
        return retrofit!!.create(APIManager::class.java)
    }
}