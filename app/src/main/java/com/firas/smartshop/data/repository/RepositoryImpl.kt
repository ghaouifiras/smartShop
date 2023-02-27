package com.firas.smartshop.data.repository

import com.firas.smartshop.data.models.CategoryProduct
import com.firas.smartshop.data.models.Login
import com.firas.smartshop.data.models.LoginResponse
import com.firas.smartshop.data.models.Product
import com.firas.smartshop.data.repository.dataSource.RemoteDataSource
import com.firas.smartshop.data.utils.ResponseState
import com.firas.smartshop.domain.repository.Repository
import retrofit2.Response
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    Repository {


    override suspend fun userLogin(login: Login): ResponseState<LoginResponse> {
        return responseToString(remoteDataSource.userLogin(login))
    }

    override suspend fun getAllArticles(): ResponseState<List<Product>> {
        return responseToShopResult(remoteDataSource.getAllArticles())
    }

    override suspend fun getAllCategories(): ResponseState<CategoryProduct> {
        return responseCategory(remoteDataSource.getAllCategories())
    }

    override suspend fun getProductsByCategory(category: String): ResponseState<List<Product>> {
        return responseToShopResult(remoteDataSource.getProductsByCategory(category))
    }

    private fun responseToString(response: Response<LoginResponse>): ResponseState<LoginResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return ResponseState.Success(it)
            }
        }
        return ResponseState.Error("${response.errorBody()?.string()}")
    }

    private fun responseToShopResult(response: Response<List<Product>>): ResponseState<List<Product>> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return ResponseState.Success(result)
            }
        }
        return ResponseState.Error("${response.errorBody()?.string()}")
    }

    private fun responseCategory(response: Response<CategoryProduct>): ResponseState<CategoryProduct> {
        if (response.isSuccessful) {
            response.body()?.let {
                return ResponseState.Success(it)
            }
        }

        return ResponseState.Error("${response.errorBody()?.string()}")

    }


}