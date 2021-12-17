package com.doepiccoding.data.repository

import com.doepiccoding.data.mapper.toCatBreed
import com.doepiccoding.data.remote.CatRestApi
import com.doepiccoding.domain.entity.action.error.ErrorEntity
import com.doepiccoding.domain.repository.CatRepository
import com.doepiccoding.domain.entity.action.Either

class RemoteCatRepository(private val api: CatRestApi): CatRepository {

    override fun getBreeds(): Either = try {
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