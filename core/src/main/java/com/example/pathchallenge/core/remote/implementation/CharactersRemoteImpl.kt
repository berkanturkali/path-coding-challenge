package com.example.pathchallenge.core.remote.implementation

import com.example.pathchallenge.core.remote.abstraction.CharactersRemote
import com.example.pathchallenge.core.remote.model.characters.CharactersResponse
import com.example.pathchallenge.core.remote.service.ApiService
import javax.inject.Inject

class CharactersRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : CharactersRemote {
    override suspend fun fetchCharacters(
        offset: Int,
        limit: Int
    ): CharactersResponse {
        return apiService.fetchCharacters(
            offset = offset,
            limit = limit
        )
    }

}