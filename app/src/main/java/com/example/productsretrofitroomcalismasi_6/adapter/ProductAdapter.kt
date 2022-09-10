package com.example.productsretrofitroomcalismasi_6.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.productsretrofitroomcalismasi_6.R
import com.example.productsretrofitroomcalismasi_6.model.Product
import com.example.productsretrofitroomcalismasi_6.util.gorselIndir
import com.example.productsretrofitroomcalismasi_6.util.placeholderYap
import com.example.productsretrofitroomcalismasi_6.view.ProductListFragmentDirections
import kotlinx.android.synthetic.main.recycler_row.view.*

class ProductAdapter(val itemList:ArrayList<Product>):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){
    class ProductViewHolder(view: View):RecyclerView.ViewHolder(view){
        var title=view.title
        val price=view.price
        val descr=view.description
        val categoryy=view.category
        val image=view.imageView
        fun bind(product: Product){
            title.setText(product.title)
            price.setText(product.price.toString())
            descr.setText(product.description)
            categoryy.setText(product.category)
            image.gorselIndir(product.image, placeholderYap(itemView.context))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val x=LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return ProductViewHolder(x)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
       val productList=itemList.get(position)
        holder.bind(productList)
        holder.itemView.setOnClickListener {
            val action=ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(productList.uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateProduct(item:List<Product>){
        itemList.clear()
        itemList.addAll(item)
        notifyDataSetChanged()
    }
}