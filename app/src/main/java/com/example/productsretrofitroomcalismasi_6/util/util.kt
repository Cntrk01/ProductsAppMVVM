package com.example.productsretrofitroomcalismasi_6.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.productsretrofitroomcalismasi_6.R


fun ImageView.gorselIndir(url: String?, placeholder : CircularProgressDrawable){

    val options = RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
    // Picasso.get().load(url).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground).into(this);
}

fun placeholderYap(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}