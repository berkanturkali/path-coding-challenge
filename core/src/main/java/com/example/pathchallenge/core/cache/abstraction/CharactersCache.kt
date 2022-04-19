package com.example.pathchallenge.core.cache.abstraction

import com.example.pathchallenge.core.cache.model.CharacterWithComics
import kotlinx.coroutines.flow.Flow

interface CharactersCache {

    suspend fun insertCharacterWithComics(
        characterWithComics: CharacterWithComics
    )

    fun getComics(characterId: Int): Flow<List<CharacterWithComics>>
}