package com.doepiccoding.advancedcleanarchitecture.di

import android.content.Context
import com.doepiccoding.advancedcleanarchitecture.ui.breeds.screen_state.BreedNetworkErrorInterpreter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideBreedNetworkErrorInterpreter(@ApplicationContext context: Context): BreedNetworkErrorInterpreter {
        return BreedNetworkErrorInterpreter(context)
    }

}