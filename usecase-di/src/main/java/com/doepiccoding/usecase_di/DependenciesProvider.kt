package com.doepiccoding.usecase_di

import com.doepiccoding.data.api.CatRestApi
import com.doepiccoding.data.datasource.RemoteBreedDataSource
import com.doepiccoding.data.repository.BasicCatRepository
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
    @RemoteApi
    fun providePrivatelySingleRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
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
    fun provideRemoteBreedDataSource(@RemoteApi catRestApi: CatRestApi): RemoteBreedDataSource {
        return RemoteBreedDataSource(catRestApi)
    }

    @Provides
    @Singleton
    fun provideGetBreedsUseCase(@RemoteApi breedDataSource: RemoteBreedDataSource): GetBreedsUseCase {
        val repository = BasicCatRepository(breedDataSource)
        return GetBreedsUseCase(repository)
    }
}