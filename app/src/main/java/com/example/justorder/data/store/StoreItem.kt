package com.example.justorder.data.store

import com.google.gson.annotations.SerializedName

data class StoreItem (

    @SerializedName("id")
    var id: Long,

    @SerializedName("name")
    var name: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("price")
    var price: Int,

    @SerializedName("quantity")
    var quantity: Int,

    @SerializedName("restraurantId")
    var restraurantId: Long,

    @SerializedName("logo")
    var logo: String
)