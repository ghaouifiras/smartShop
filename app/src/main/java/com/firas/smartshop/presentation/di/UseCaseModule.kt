package com.firas.smartshop.presentation.di

import android.content.SharedPreferences
import com.firas.smartshop.domain.repository.Repository
import com.firas.smartshop.domain.useCase.AuthentificationUseCase
import com.firas.smartshop.domain.useCase.SplashScreenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun providesAuthUseCase(shopDomainRepository: Repository): AuthentificationUseCase {
        return AuthentificationUseCase(shopDomainRepository)
    }


}