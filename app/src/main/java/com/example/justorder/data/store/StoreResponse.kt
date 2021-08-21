package com.example.justorder.data.store

import com.google.gson.annotations.SerializedName

data class StoreResponse (

     @SerializedName("result")
     var list: ArrayList<StoreItem> ,

     @SerializedName("status")
     var status: Int,

     @SerializedName("message")
     var message: String
)