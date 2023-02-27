package com.firas.smartshop.domain.repository

import com.firas.smartshop.data.models.CategoryProduct
import com.firas.smartshop.data.models.Login
import com.firas.smartshop.data.models.LoginResponse
import com.firas.smartshop.data.models.Product
import com.firas.smartshop.data.utils.ResponseState

interface Repository {

    suspend fun userLogin(login: Login): ResponseState<LoginResponse>

    suspend fun getAllArticles(): ResponseState<List<Product>>

    suspend fun getAllCategories () : ResponseState<CategoryProduct>

    suspend fun getProductsByCategory(category : String) :ResponseState<List<Product>>
}