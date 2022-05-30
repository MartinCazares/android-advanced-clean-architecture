package com.doepiccoding.data.mapper

import com.doepiccoding.data.api.dto.CatBreedDto
import com.doepiccoding.domain.entity.CatBreed

fun CatBreedDto.toCatBreed() = CatBreed(breed, country, origin, coat, pattern)

//TODO: Add more mappers if necessary for other representations (e.g. DB entity to Domain entity)