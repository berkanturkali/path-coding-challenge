package com.example.pathchallenge.core.remote.implementation

import com.example.pathchallenge.core.remote.abstraction.CharactersRemote
import com.example.pathchallenge.core.remote.model.characters.CharactersResponse
import com.example.pathchallenge.core.remote.service.ApiService
import javax.inject.Inject

class CharactersRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : CharactersRemote {
    override suspend fun fetchCharacters(
        hash: String,
        ts: String,
        offset: Int,
        limit: Int
    ): CharactersResponse {
        return apiService.fetchCharacters(
            hash = hash,
            timestamp = ts,
            offset = offset,
            limit = limit
        )
    }

}