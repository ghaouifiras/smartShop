package com.firas.smartshop.data.utils

sealed class LoginUiState() {

    object Success : LoginUiState()
    object Loading : LoginUiState()
    data class Error(val message: String) : LoginUiState()
    object Empty : LoginUiState()
}
