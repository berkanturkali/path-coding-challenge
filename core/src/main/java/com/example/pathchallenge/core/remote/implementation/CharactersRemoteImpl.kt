package com.example.pathchallenge.core.remote.implementation

import com.example.pathchallenge.core.remote.abstraction.CharactersRemote
import com.example.pathchallenge.core.remote.model.characters.CharactersResponse
import com.example.pathchallenge.core.remote.model.comics.ComicsResponse
import com.example.pathchallenge.core.remote.service.ApiService
import com.example.pathchallenge.core.util.Constants.HASH_FORMAT
import com.example.pathchallenge.core.util.Constants.PRIVATE_API_KEY
import com.example.pathchallenge.core.util.Constants.PUBLIC_API_KEY
import com.example.pathchallenge.core.util.extensions.toMD5
import retrofit2.Response
import javax.inject.Inject

class CharactersRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : CharactersRemote {

    companion object {
        val ts = System.currentTimeMillis().toString()
        val hash = HASH_FORMAT.format(ts, PRIVATE_API_KEY, PUBLIC_API_KEY).toMD5()
    }

    override suspend fun fetchCharacters(
        offset: Int,
        limit: Int
    ): CharactersResponse {
        return apiService.fetchCharacters(
            ts = ts,
            hash = hash,
            offset = offset,
            limit = limit
        )
    }

    override suspend fun fetchComics(id: Int): Response<ComicsResponse> {
        return apiService.fetchComics(id, ts = ts, hash = hash)
    }
}