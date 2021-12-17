package com.doepiccoding.usecase_di

import com.doepiccoding.data.remote.CatRestApi
import com.doepiccoding.data.repository.RemoteCatRepository
import com.doepiccoding.domain.usecase.GetBreedsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object DependenciesProvider {

    @Provides
    @Singleton
    fun provideGetBreedsUseCase(): GetBreedsUseCase {
        val retrofit = providePrivatelySingleRetrofit()
        val catRestApi = providePrivatelySingleCatRestApi(retrofit)
        val repository = RemoteCatRepository(catRestApi)
        return GetBreedsUseCase(repository)
    }

    /**
     * Helper methods to prevent exposing types
     * along with other information strictly related
     * to the "data" layer.
     */
    private lateinit var catRestApi: CatRestApi
    @Synchronized
    private fun providePrivatelySingleCatRestApi(retrofit: Retrofit): CatRestApi {
        if (!::catRestApi.isInitialized) {
            catRestApi = retrofit.create(CatRestApi::class.java)
        }
        return catRestApi
    }

    private lateinit var retrofit: Retrofit
    @Synchronized
    fun providePrivatelySingleRetrofit(): Retrofit {
        if (!::retrofit.isInitialized) {
            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        }
        return retrofit
    }
}