package com.doepiccoding.data.repository

import com.doepiccoding.data.datasource.RemoteBreedDataSource
import com.doepiccoding.domain.entity.action.Either
import com.doepiccoding.domain.entity.action.error.ErrorEntity
import com.doepiccoding.domain.repository.CatRepository

class BasicCatRepository(private val dataSource: RemoteBreedDataSource): CatRepository {

    override fun getBreeds(): Either  {
        /**
         * This would be the point of flow where
         * you can decide whether if you want to
         * provide the breeds locally or remotely
         * (adding the local dependency in the constructor)...
         */
        return dataSource.getBreeds()
    }

}