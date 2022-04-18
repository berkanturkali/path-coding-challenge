package com.example.pathchallenge.core.remote.service

import com.example.pathchallenge.core.remote.model.characters.CharactersResponse
import com.example.pathchallenge.core.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.characters)
    suspend fun fetchCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): CharactersResponse

}