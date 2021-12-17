package com.doepiccoding.domain.repository

import com.doepiccoding.domain.usecase.UseCaseResult

interface CatRepository {
    fun getBreeds(): UseCaseResult
}