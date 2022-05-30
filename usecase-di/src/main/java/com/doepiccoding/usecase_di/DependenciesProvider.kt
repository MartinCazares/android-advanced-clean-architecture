package com.doepiccoding.usecase_di

import com.doepiccoding.data.remote.CatRestApi
import com.doepiccoding.data.repository.RemoteCatRepository
import com.doepiccoding.domain.usecase.GetBreedsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DependenciesProvider {

    @Provides
    @Singleton
    fun provideGetBreedsUseCase(@RemoteApi catRestApi: CatRestApi): GetBreedsUseCase {
        val repository = RemoteCatRepository(catRestApi)
        return GetBreedsUseCase(repository)
    }

    @Provides
    @Singleton
    @RemoteApi
    fun providePrivatelySingleCatRestApi(@RemoteApi retrofit: Retrofit): CatRestApi {
            return retrofit.create(CatRestApi::class.java)
    }

    @Provides
    @Singleton
    @RemoteApi
    fun providePrivatelySingleRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}