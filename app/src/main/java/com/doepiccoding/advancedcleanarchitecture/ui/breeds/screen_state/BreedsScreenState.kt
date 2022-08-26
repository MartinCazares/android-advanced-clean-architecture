package com.doepiccoding.advancedcleanarchitecture.ui.breeds.screen_state

import com.doepiccoding.domain.entity.CatBreed
import com.doepiccoding.domain.entity.action.error.ErrorEntity

sealed interface BreedsScreenState {
    object OnLoading: BreedsScreenState
    data class OnBreedsLoaded(val breeds: List<CatBreed>): BreedsScreenState
    data class OnError(val error: ErrorEntity): BreedsScreenState
}