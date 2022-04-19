package com.example.pathchallenge.core.cache.implementation

import com.example.pathchallenge.core.cache.abstraction.CharactersCache
import com.example.pathchallenge.core.cache.dao.ComicsDao
import com.example.pathchallenge.core.cache.model.CharacterWithComics
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersCacheImpl @Inject constructor(
    private val dao: ComicsDao
) : CharactersCache {

    override suspend fun insertCharacterWithComics(
        characterWithComics: CharacterWithComics
    ) {
        return dao.insertCharacterWithComics(characterWithComics)
    }

    override fun getComics(characterId: Int): Flow<List<CharacterWithComics>> {
        return dao.getComics(characterId)
    }
}