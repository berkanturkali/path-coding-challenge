package com.example.pathchallenge.core.cache.abstraction

import com.example.pathchallenge.core.cache.model.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharactersCache {
    suspend fun upsert(characterEntity: CharacterEntity): Long

    fun characters(): Flow<List<CharacterEntity>>

    suspend fun delete(characterEntity: CharacterEntity)


    suspend fun character(id: Int): CharacterEntity?
}