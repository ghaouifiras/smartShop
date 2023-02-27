package com.firas.smartshop.data.api

import com.firas.smartshop.data.models.CategoryProduct
import com.firas.smartshop.data.models.Login
import com.firas.smartshop.data.models.LoginResponse
import com.firas.smartshop.data.models.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("auth/login")
    suspend fun userLogin(@Body login: Login): Response<LoginResponse>

    @GET("products")
    suspend fun getAllArticles(): Response<List<Product>>

    @GET("products/categories")
    suspend fun getAllCategories(): Response<CategoryProduct>

    @GET("products/category/{nameCategory}")
    suspend fun getProductsByCategory(@Path(value = "nameCategory") nameCategory: String): Response<List<Product>>

}