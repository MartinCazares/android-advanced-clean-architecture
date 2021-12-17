package com.doepiccoding.data.repository

import com.doepiccoding.data.mapper.toCatBreed
import com.doepiccoding.data.remote.CatRestApi
import com.doepiccoding.domain.entity.error.ErrorEntity
import com.doepiccoding.domain.repository.CatRepository
import com.doepiccoding.domain.usecase.UseCaseResult

class RemoteCatRepository(private val api: CatRestApi): CatRepository {

    override fun getBreeds(): UseCaseResult = try {
        val response = api.getBreeds().execute()
        if (response.isSuccessful) {
            response.body()?.let { body ->
                UseCaseResult.Success ( body.data.map { dto -> dto.toCatBreed() } )
            } ?: UseCaseResult.Error(ErrorEntity.EmptyResponseError)
        } else {
            UseCaseResult.Error(ErrorEntity.NetworkError(response.code()))
        }
    } catch (e: Exception) {
        UseCaseResult.Error(ErrorEntity.UnknownError(e))
    }
}