package com.example.productsretrofitroomcalismasi_6.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.productsretrofitroomcalismasi_6.database.ProductDatabase
import com.example.productsretrofitroomcalismasi_6.model.Product
import com.example.productsretrofitroomcalismasi_6.service.ProductService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ProductListViewModel(application: Application) :BaseViewModel(application) {
    val product = MutableLiveData<List<Product>>()
    val productExcept = MutableLiveData<Boolean>()
    val productLoading = MutableLiveData<Boolean>()
    private val productService = ProductService()
    private val disposable = CompositeDisposable()

    fun dataProductFromInternet(){
        getDataFromApi()
    }

    private fun getDataFromApi() {
        productLoading.value=true
        disposable.add(
            productService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Product>>(){
                    override fun onSuccess(t: List<Product>) {
                       sqLiteDatabase(t)
                    }

                    override fun onError(e: Throwable) {
                        productExcept.value=true
                        productLoading.value=false
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun showProduct(productt: List<Product>){
        product.value=productt
        productExcept.value=false
        productLoading.value=false
    }

    private fun sqLiteDatabase(product:List<Product>){
        launch {
            val dao=ProductDatabase(getApplication()).productDao()
            dao.deleteProduct()
            val uuidList=dao.insertAllProduct(*product.toTypedArray())
            var i=0
            while (i<product.size){
                product[i].uuid=uuidList[i].toInt()
                i+=1
            }
        showProduct(product)

        }
        getProductSqLite()
    }

     fun getProductSqLite(){
        launch {
            val productList=ProductDatabase(getApplication()).productDao().getAllProduct()
            showProduct(productList)
            Toast.makeText(getApplication(),"ROOM",Toast.LENGTH_SHORT).show()
        }
    }

}