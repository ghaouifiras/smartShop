package com.firas.smartshop.data.utils

sealed class ResponseState<out T>{

    data class Success<out T>(val data: T? = null) : ResponseState<T>()
    data class Loading(val nothing: Nothing?=null) : ResponseState<Nothing>()
    data class Error(val msg: String?) : ResponseState<Nothing>()
}
