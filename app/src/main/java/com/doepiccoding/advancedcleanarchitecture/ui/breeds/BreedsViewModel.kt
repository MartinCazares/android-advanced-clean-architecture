package com.doepiccoding.advancedcleanarchitecture.ui.breeds

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doepiccoding.advancedcleanarchitecture.ui.breeds.screen_state.BreedsScreenState
import com.doepiccoding.domain.usecase.GetBreedsUseCase
import com.doepiccoding.domain.entity.action.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class BreedsViewModel @Inject constructor(
    private val getBreedsUseCase: GetBreedsUseCase,
    private val coroutineContext: CoroutineContext): ViewModel() {

    private val _breeds = MutableLiveData<BreedsScreenState>()
    val breeds: LiveData<BreedsScreenState> = _breeds

    fun fetchBreeds() {
        viewModelScope.launch(coroutineContext) {
            runWithLoadingState(_breeds) {

                // ============ Simulate taking a bit long to fetch data ============
                delay(1500)
                // ==================================================================

                when (val result = getBreedsUseCase()) {
                    is Either.Success -> {
                        _breeds.emit(BreedsScreenState.OnBreedsLoaded(result.getData()))
                    }
                    is Either.Error -> {
                        _breeds.emit(BreedsScreenState.OnError(result.error))
                    }
                }
            }
        }
    }

    private suspend inline fun runWithLoadingState(componentAction: MutableLiveData<BreedsScreenState>, block: () -> Unit) =
        try {
            componentAction.emit(BreedsScreenState.OnLoading(true))
            block()
        } finally {
            componentAction.emit(BreedsScreenState.OnLoading(false))
        }

    private suspend fun <T> MutableLiveData<T>.emit(any: T) = withContext(Dispatchers.Main.immediate) { value = any }
}