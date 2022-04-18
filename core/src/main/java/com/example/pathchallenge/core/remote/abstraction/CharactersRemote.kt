package com.example.pathchallenge.core.remote.abstraction

import com.example.pathchallenge.core.remote.model.characters.CharactersResponse

interface CharactersRemote {

    suspend fun fetchCharacters(
        hash: String,
        ts: String,
        offset: Int,
        limit: Int
    ): CharactersResponse
}