package com.firas.smartshop.domain.useCase

import android.util.Log
import com.firas.smartshop.data.models.Login
import com.firas.smartshop.data.models.LoginResponse
import com.firas.smartshop.data.utils.ResponseState
import com.firas.smartshop.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class AuthentificationUseCase @Inject constructor(private val shopDomainRepository: Repository) {

    fun userLogin(name: String, password: String): Flow<ResponseState<LoginResponse>> = flow {
        emit(ResponseState.Loading())

        try {
            val login = Login(name, password)
            val response = shopDomainRepository.userLogin(login)
            emit(response)
        } catch (e: HttpException) {
            Log.i("AuthUseCase", "${e.localizedMessage}")
            emit(ResponseState.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.i("AuthUseCase", "${e.localizedMessage}")
            emit(ResponseState.Error("Couldn't reach server. Check your internet connection."))
        }


    }


}