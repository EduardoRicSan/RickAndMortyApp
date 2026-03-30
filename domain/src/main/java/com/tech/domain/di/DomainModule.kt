package com.tech.domain.di

import com.tech.data.remote.api.RMApiService
import com.tech.domain.repository.RMRepository
import com.tech.domain.repository.RMRepositoryImpl
import com.tech.domain.useCase.GetAllCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun providesRMRepository(
        apiService: RMApiService,
    ): RMRepository = RMRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideGetAllCharactersUseCase(
        rmRepository: RMRepository,
    ): GetAllCharactersUseCase =
        GetAllCharactersUseCase(rmRepository)
}