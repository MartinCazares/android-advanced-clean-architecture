package com.doepiccoding.domain.repository

import com.doepiccoding.domain.entity.action.Either

interface CatRepository {
    fun getBreeds(): Either
}