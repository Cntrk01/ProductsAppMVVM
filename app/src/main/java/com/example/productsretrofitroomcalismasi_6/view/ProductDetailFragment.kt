package com.example.productsretrofitroomcalismasi_6.view

import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableResource
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder.COMPRESSION_FORMAT
import com.example.productsretrofitroomcalismasi_6.R
import com.example.productsretrofitroomcalismasi_6.viewmodel.ProductDetailViewModel
import kotlinx.android.synthetic.main.fragment_product_detail.*
import kotlinx.android.synthetic.main.recycler_row.*
import java.util.*

class ProductDetailFragment : Fragment() {
    private var productID=0
    private lateinit var viewModel : ProductDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            productID=ProductDetailFragmentArgs.fromBundle(it).id
        }
        viewModel=ViewModelProvider(requireActivity()).get(ProductDetailViewModel::class.java)
        viewModel.roomGetData(productID)
        observerLiveData()
    }

    private fun observerLiveData() {
        viewModel.productLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
                productDetailCategory.text=it.category
                productDetailDescription.text=it.description
                productDetailPrice.text=it.price.toString()
                productDetailTitle.text=it.title
                Glide.with(productDetailImage).load(it.image).into(productDetailImage)
            }
        })
    }
}

