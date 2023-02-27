package com.firas.smartshop.presentation.di

import com.firas.smartshop.data.repository.RepositoryImpl
import com.firas.smartshop.data.repository.dataSource.RemoteDataSource
import com.firas.smartshop.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: RemoteDataSource): Repository {
        return RepositoryImpl(remoteDataSource)
    }
}