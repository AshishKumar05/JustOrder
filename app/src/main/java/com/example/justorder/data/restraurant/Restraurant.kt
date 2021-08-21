package com.example.justorder.data.restraurant

import com.example.justorder.data.store.StoreItem
import com.google.gson.annotations.SerializedName


data class Restraurant (
    @SerializedName("restraurantId")
    var restraurantId: Long,

    @SerializedName("logo")
    var logo: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("rating")
    var rating: Double,

    @SerializedName("location")
    var location: String,

    @SerializedName("offerpercent")
    var offerpercent: String,

    @SerializedName("maxoffer")
    var maxoffer: Int,

    @SerializedName("specialMsg")
    var specialMsg: String
)