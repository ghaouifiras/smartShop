package com.firas.smartshop.data.repository.dataSourceImpl

import com.firas.smartshop.data.api.ApiService
import com.firas.smartshop.data.models.CategoryProduct
import com.firas.smartshop.data.models.Login
import com.firas.smartshop.data.models.LoginResponse
import com.firas.smartshop.data.models.Product
import com.firas.smartshop.data.repository.dataSource.RemoteDataSource
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    RemoteDataSource {

    override suspend fun userLogin(login: Login): Response<LoginResponse> =
        apiService.userLogin(login)

    override suspend fun getAllArticles(): Response<List<Product>> {
        return apiService.getAllArticles()
    }

    override suspend fun getAllCategories(): Response<CategoryProduct> {
        return apiService.getAllCategories()
    }

    override suspend fun getProductsByCategory(category: String): Response<List<Product>> {
        return apiService.getProductsByCategory(category)
    }
}