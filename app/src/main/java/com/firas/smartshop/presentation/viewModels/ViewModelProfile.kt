package com.firas.smartshop.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.firas.smartshop.data.utils.SharedPreferencesUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelProfile @Inject constructor(
    val sharedPreferencesUtils: SharedPreferencesUtils)
    :  ViewModel(){

    fun logout(){
        sharedPreferencesUtils.deleteAccessToken()

    }




}