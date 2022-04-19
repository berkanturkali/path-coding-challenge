package com.example.pathchallenge.core.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.pathchallenge.core.cache.model.CharacterEntity
import com.example.pathchallenge.core.cache.model.CharacterWithComics
import com.example.pathchallenge.core.cache.model.ComicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ComicsDao {

    @Insert
    suspend fun insertComics(comic: List<ComicEntity>)

    @Insert
    suspend fun insertCharacter(character: CharacterEntity)

    @Transaction
    suspend fun insertCharacterWithComics(charAndComics: CharacterWithComics) {
        insertCharacter(charAndComics.characterEntity)
        insertComics(charAndComics.comics)
    }

    @Transaction
    @Query("SELECT * FROM comics WHERE characterId =:characterId")
    fun getComics(characterId: Int): Flow<List<CharacterWithComics>>
}