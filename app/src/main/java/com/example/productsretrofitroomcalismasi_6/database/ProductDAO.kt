package com.example.productsretrofitroomcalismasi_6.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.productsretrofitroomcalismasi_6.model.Product

@Dao
interface ProductDAO {
    @Insert
    suspend fun insertAllProduct(vararg product: Product):List<Long>

    @Query("DELETE FROM product")
    suspend fun deleteProduct()

    @Query("SELECT*FROM product")
    suspend fun getAllProduct():List<Product>

    @Query("SELECT*FROM product WHERE uuid=:productID")
    suspend fun getProduct(productID:Int):Product
}