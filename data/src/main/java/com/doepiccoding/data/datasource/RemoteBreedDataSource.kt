package com.doepiccoding.data.datasource

import com.doepiccoding.data.api.CatRestApi
import com.doepiccoding.data.mapper.toCatBreed
import com.doepiccoding.domain.entity.action.Either
import com.doepiccoding.domain.entity.action.error.ErrorEntity

class RemoteBreedDataSource(private val api: CatRestApi) {

    fun getBreeds(): Either = try {
        val response = api.getBreeds().execute()
        if (response.isSuccessful) {
            response.body()?.let { body ->
                Either.Success ( body.data.map { dto -> dto.toCatBreed() } )
            } ?: Either.Error(ErrorEntity.EmptyResponseError)
        } else {
            Either.Error(ErrorEntity.NetworkError(response.code()))
        }
    } catch (e: Exception) {
        Either.Error(ErrorEntity.UnknownError(e))
    }
}