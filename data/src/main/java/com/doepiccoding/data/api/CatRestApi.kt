package com.doepiccoding.data.api

import com.doepiccoding.data.api.dto.CatBreedResponseDto
import retrofit2.Call
import retrofit2.http.GET

interface CatRestApi {

    @GET("/breeds")
    fun getBreeds(): Call<CatBreedResponseDto>

}