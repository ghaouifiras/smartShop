package com.firas.smartshop.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firas.smartshop.data.models.Product
import com.firas.smartshop.databinding.SingleItemBinding

class ProductAdapter(private var list: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var onItemClickListener : ((Product)-> Unit) = {}

    fun setOnItemClickListener(listener : (Product)-> Unit){
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = SingleItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class ProductViewHolder(private val binding: SingleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(shopItem: Product) {

            Glide.with(binding.itemImage)
                .load(shopItem.image)
                .into(binding.itemImage)

            binding.itemTitle.text = shopItem.title
            binding.itemPrice.text = "USD ${shopItem.price}"
            binding.itemRating.text = "${2}"
            binding.itemReview.text = "${20} Reviews"

            binding.itemView.setOnClickListener {
                onItemClickListener(shopItem)
            }
        }

    }


    fun setData(list: List<Product>) {
        this.list = list
        notifyDataSetChanged()
    }

}