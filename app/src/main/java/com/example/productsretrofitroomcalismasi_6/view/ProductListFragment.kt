package com.example.productsretrofitroomcalismasi_6.view

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productsretrofitroomcalismasi_6.R
import com.example.productsretrofitroomcalismasi_6.adapter.ProductAdapter
import com.example.productsretrofitroomcalismasi_6.viewmodel.ProductListViewModel
import kotlinx.android.synthetic.main.fragment_product_list.*


class ProductListFragment : Fragment() {
    private lateinit var viewModel:ProductListViewModel
    private var productAdapter=ProductAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this).get(ProductListViewModel::class.java)
        viewModel.getProductSqLite()
        productListRecycler.layoutManager=LinearLayoutManager(requireContext())
        productListRecycler.adapter=productAdapter
        swipeRefreshLayout.setOnRefreshListener {
            productLoading.visibility=View.VISIBLE
            productExcept.visibility=View.GONE
            productListRecycler.visibility=View.GONE
            viewModel.dataProductFromInternet()
            swipeRefreshLayout.isRefreshing=false
            Toast.makeText(context,"İnternet",Toast.LENGTH_LONG).show()
        }
        observer()
    }
    private fun observer(){
        viewModel.product.observe(viewLifecycleOwner, Observer {
            it?.let {
                productListRecycler.visibility=View.VISIBLE
                productAdapter.updateProduct(it)
            }
        })
        viewModel.productLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    productListRecycler.visibility = View.GONE
                    productExcept.visibility = View.GONE
                    productLoading.visibility = View.VISIBLE
                } else {
                    productLoading.visibility = View.GONE
                }
            }
        })
        viewModel.productExcept.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    productExcept.visibility = View.VISIBLE
                    productListRecycler.visibility = View.GONE
                } else {
                    productExcept.visibility = View.GONE
                }
            }

        })
    }
}