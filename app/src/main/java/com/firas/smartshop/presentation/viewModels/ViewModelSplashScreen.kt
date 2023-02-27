package com.firas.smartshop.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.firas.smartshop.domain.useCase.SplashScreenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ViewModelSplashScreen @Inject constructor(private val splashScreenUseCase: SplashScreenUseCase) :
    ViewModel() {

    private val _uiStateFlow: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    var uiStateFlow = _uiStateFlow.asStateFlow()


    fun direction() {
        _uiStateFlow.value = splashScreenUseCase.isLogged()


    }

}