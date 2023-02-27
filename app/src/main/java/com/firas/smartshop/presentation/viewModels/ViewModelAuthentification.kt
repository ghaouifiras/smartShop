package com.firas.smartshop.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firas.smartshop.data.utils.LoginUiState
import com.firas.smartshop.data.utils.ResponseState
import com.firas.smartshop.data.utils.SharedPreferencesUtils
import com.firas.smartshop.domain.useCase.AuthentificationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelAuthentification @Inject constructor(
    private val useCase: AuthentificationUseCase,
    private val sharedPreferenceUtils: SharedPreferencesUtils
) :
    ViewModel() {

    private var _response: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState.Empty)
    var response = _response.asStateFlow()

    private fun saveAutToken(token: String) = sharedPreferenceUtils.saveUserAccessToken(token)

    fun loginUser(name: String, password: String) {
        viewModelScope.launch {
            useCase.userLogin(name, password).collect() {
                when (it) {

                    is ResponseState.Loading -> {
                        Log.d("im here loading as Fuck", "")
                        _response.value = LoginUiState.Loading
                    }
                    is ResponseState.Error -> {
                        // response.emit(false)
                        Log.d("im here Error as Fuck", "")

                        _response.value = LoginUiState.Error(it.msg.toString())

                    }
                    is ResponseState.Success -> {
                        //  response.emit(true)
                        Log.d("im here suuuuuuuc as Fuck", "")

                        it.data?.let { it1 -> saveAutToken(it1.token) }

                        _response.value = LoginUiState.Success
                    }
                }


            }
        }

    }
}