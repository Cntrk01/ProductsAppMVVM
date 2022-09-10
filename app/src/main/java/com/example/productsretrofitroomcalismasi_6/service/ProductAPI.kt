package com.example.productsretrofitroomcalismasi_6.service

import com.example.productsretrofitroomcalismasi_6.model.Product
import io.reactivex.Single
import retrofit2.http.GET

interface ProductAPI {
    @GET("/products?limit=5")
    fun getProductAPI():Single<List<Product>>
}