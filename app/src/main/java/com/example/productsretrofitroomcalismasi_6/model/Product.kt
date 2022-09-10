package com.example.productsretrofitroomcalismasi_6.model

import android.net.Uri
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Product(
    @SerializedName("title")
    val title:String,
    @SerializedName("price")
    val price:Double,
    @SerializedName("description")
    val description:String,
    @SerializedName("category")
    val category:String,
    @SerializedName("image")
    val image:String
):Parcelable{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}
