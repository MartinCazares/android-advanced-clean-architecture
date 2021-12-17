package com.doepiccoding.data.remote

import com.doepiccoding.data.remote.dto.CatBreedResponseDto
import retrofit2.Call
import retrofit2.http.GET

interface CatRestApi {

    @GET("/breeds")
    fun getBreeds(): Call<CatBreedResponseDto>

}