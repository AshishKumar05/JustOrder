package com.example.justorder.data

import com.example.justorder.data.restraurant.RestraurantResponse
import com.example.justorder.data.store.StoreResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface APIManager {

    @GET("/productlist")
    fun getItemList(): Call<StoreResponse>

    @GET("/restraurant_items/{Id}")
    fun getRestraurantItemList(@Path("Id") Id:String): Call<StoreResponse>

    @GET("/restraurants")
    fun getRestraurants(): Call<RestraurantResponse>

    @GET("/restraurant/{Id}")
    fun getOneRestraurant(@Path("Id") Id:String): Call<RestraurantResponse>
}