package com.firas.smartshop.data.repository.dataSource

import com.firas.smartshop.data.models.CategoryProduct
import com.firas.smartshop.data.models.Login
import com.firas.smartshop.data.models.LoginResponse
import com.firas.smartshop.data.models.Product
import retrofit2.Response

interface RemoteDataSource {

    suspend fun userLogin(login: Login): Response<LoginResponse>

    suspend fun getAllArticles () : Response<List<Product>>

    suspend fun getAllCategories () : Response<CategoryProduct>

    suspend fun getProductsByCategory(category : String):Response<List<Product>>
}