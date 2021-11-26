package com.example.justorder.data.restraurant

import com.example.justorder.data.store.StoreItem
import com.google.gson.annotations.SerializedName

data class RestraurantResponse (

        @SerializedName("result")
        var list: List<Restraurant>,

        @SerializedName("status")
        var status: Int,

        @SerializedName("message")
        var message: String
)