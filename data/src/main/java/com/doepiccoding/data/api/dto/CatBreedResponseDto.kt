package com.doepiccoding.data.api.dto

import com.squareup.moshi.Json

data class CatBreedResponseDto (
    @field:Json(name = "data")
    val data: List<CatBreedDto>)

data class CatBreedDto (
    @field:Json(name = "breed")
    val breed: String,
    @field:Json(name = "country")
    val country: String,
    @field:Json(name = "origin")
    val origin: String,
    @field:Json(name = "coat")
    val coat: String,
    @field:Json(name = "pattern")
    val pattern: String
)