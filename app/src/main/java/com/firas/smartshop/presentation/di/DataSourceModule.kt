package com.firas.smartshop.presentation.di

import com.firas.smartshop.data.api.ApiService
import com.firas.smartshop.data.repository.dataSource.RemoteDataSource
import com.firas.smartshop.data.repository.dataSourceImpl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Singleton
    @Provides
    fun provideShopRemoteDataSource(apiService: ApiService) : RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }

}