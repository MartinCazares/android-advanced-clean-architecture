package com.doepiccoding.domain.usecase

import com.doepiccoding.domain.repository.CatRepository

class GetBreedsUseCase(private val catRepository: CatRepository) {
    operator fun invoke(): UseCaseResult {
        return catRepository.getBreeds()
    }
}