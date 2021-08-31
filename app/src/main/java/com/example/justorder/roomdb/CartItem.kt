package com.example.justorder.roomdb

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartItem(

        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        var id: Long ,

        @ColumnInfo(name = "name")
        var name: String ,

        @ColumnInfo(name = "description")
        var description: String,

        @ColumnInfo(name = "price")
        var price: Int ,

        @ColumnInfo(name = "orderquantity")
        var orderquantity: Int ,

        @ColumnInfo(name = "restraurantId")
        var restraurantId: Long ,

        @ColumnInfo(name = "logo")
        var logo: String

)