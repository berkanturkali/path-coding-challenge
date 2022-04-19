package com.example.pathchallenge.core.cache.implementation

import com.example.pathchallenge.core.cache.abstraction.CharactersCache
import com.example.pathchallenge.core.cache.dao.CharactersDao
import com.example.pathchallenge.core.cache.model.CharacterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersCacheImpl @Inject constructor(
    private val dao: CharactersDao
) : CharactersCache {
    override suspend fun upsert(characterEntity: CharacterEntity): Long {
        return dao.upsert(characterEntity)
    }

    override fun characters(): Flow<List<CharacterEntity>> {
        return dao.characters()
    }

    override suspend fun delete(characterEntity: CharacterEntity) {
        dao.delete(characterEntity)
    }

    override suspend fun character(id: Int): CharacterEntity? {
        return dao.character(id)
    }
}