package com.firas.smartshop.domain.useCase

import android.util.Log
import com.firas.smartshop.data.models.CategoryProduct
import com.firas.smartshop.data.utils.ResponseState
import com.firas.smartshop.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class HomeFragmentCategoryUseCase @Inject constructor(private val repository: Repository) {


    fun getAllCategories(): Flow<ResponseState<CategoryProduct>> = flow {
        emit(ResponseState.Loading())

        try {

            emit(repository.getAllCategories())

        } catch (e: HttpException) {
            Log.i("HomeFragmentCategory", "${e.localizedMessage}")
            emit(ResponseState.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: java.io.IOException) {
            Log.i("HomeFragmentCategory", "${e.localizedMessage}")
            emit(ResponseState.Error("Couldn't reach server. Check your internet connection."))
        }

    }.flowOn(Dispatchers.IO)

}