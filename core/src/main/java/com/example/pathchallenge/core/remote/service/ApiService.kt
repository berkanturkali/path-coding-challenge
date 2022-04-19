package com.example.pathchallenge.core.remote.service

import com.example.pathchallenge.core.remote.model.characters.CharactersResponse
import com.example.pathchallenge.core.remote.model.comics.ComicsResponse
import com.example.pathchallenge.core.util.Constants
import com.example.pathchallenge.core.util.Constants.DATE_RANGE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.CHARACTERS)
    suspend fun fetchCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): CharactersResponse

    @GET(Constants.CHARACTERS_COMICS)
    suspend fun fetchComics(
        @Path("characterId") id: Int,
        @Query("limit") limit: Int = 10,
        @Query("dateRange", encoded = true) date: String = DATE_RANGE,
    ): ComicsResponse
}