package com.firas.smartshop.domain.useCase

import com.firas.smartshop.data.models.Product
import com.firas.smartshop.data.utils.ResponseState
import com.firas.smartshop.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeFragmentGetAllArticleUseCase @Inject constructor(private val repository: Repository) {

    fun getAllProducts(): Flow<ResponseState<List<Product>>> = flow {

        emit(ResponseState.Loading())
        try {
            emit(repository.getAllArticles())

        } catch (e: Exception) {

            emit(ResponseState.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)



    fun getAllProductsByCategory(category:String): Flow<ResponseState<List<Product>>> = flow {

        emit(ResponseState.Loading())
        try {
            emit(repository.getProductsByCategory(category))

        } catch (e: Exception) {

            emit(ResponseState.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)

}