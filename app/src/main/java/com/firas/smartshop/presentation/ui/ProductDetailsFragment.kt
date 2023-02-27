package com.firas.smartshop.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.firas.smartshop.R
import com.firas.smartshop.data.models.Product
import com.firas.smartshop.databinding.DetailProductLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {
    private lateinit var product: Product
    private lateinit var binding: DetailProductLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_product_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        product = ProductDetailsFragmentArgs.fromBundle(requireArguments()).product

        binding = DetailProductLayoutBinding.bind(view)
        updateViews()
        navigation()

    }


    private fun updateViews() {

        binding.productDetailPrice.text = "USD ${product.price}"
        binding.productDetailTitle.text = product.title
        binding.productDetailDescription.text = product.description

        Glide.with(binding.productDetailImage)
            .load(product.image)
            .into(binding.productDetailImage)

        binding.productDetailRating.text = "${2}"
        binding.productDetailReviews.text = "${20} Reviews"

    }

    private fun navigation() {
        binding.productDetailBack.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }
}