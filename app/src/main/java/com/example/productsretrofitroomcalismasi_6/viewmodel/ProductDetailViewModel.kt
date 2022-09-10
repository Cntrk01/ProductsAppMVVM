package com.example.productsretrofitroomcalismasi_6.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.productsretrofitroomcalismasi_6.database.ProductDatabase
import com.example.productsretrofitroomcalismasi_6.model.Product
import kotlinx.coroutines.launch

class ProductDetailViewModel(application: Application):BaseViewModel(application) {
    val productLiveData=MutableLiveData<Product>()

    fun roomGetData(uuid:Int){
         getDATA(uuid)
    }

    private fun getDATA(id:Int) {
        launch {
            val deger=ProductDatabase(getApplication()).productDao().getProduct(id)
            productLiveData.value=deger
        }
    }
}