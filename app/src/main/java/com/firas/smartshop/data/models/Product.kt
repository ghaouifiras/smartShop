package com.firas.smartshop.data.models

data class Product(
    val id: Int,
    val title: String,
    val price: String,
    val category: String,
    val description: String,
    val image: String
):java.io.Serializable
