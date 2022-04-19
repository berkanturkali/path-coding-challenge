package com.example.pathchallenge.core.cache.dao

import androidx.room.*
import com.example.pathchallenge.core.cache.model.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(characterEntity: CharacterEntity): Long

    @Query("SELECT * FROM characters")
    fun characters(): Flow<List<CharacterEntity>>

    @Delete
    suspend fun delete(favoriteEntity: CharacterEntity)

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun character(id: Int): CharacterEntity?
}