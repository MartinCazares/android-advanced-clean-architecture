package com.doepiccoding.advancedcleanarchitecture.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object DependenciesProvider {
    @Provides
    @Singleton
    fun provideIOCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }
}