package com.firas.smartshop.domain.useCase

import android.content.SharedPreferences
import com.firas.smartshop.data.utils.SharedPreferencesUtils
import javax.inject.Inject

class SplashScreenUseCase @Inject constructor(private val sharedPreferencesUtils: SharedPreferencesUtils) {


    fun isLogged(): Boolean {

        return sharedPreferencesUtils.userIsLoggedIn()

    }
}