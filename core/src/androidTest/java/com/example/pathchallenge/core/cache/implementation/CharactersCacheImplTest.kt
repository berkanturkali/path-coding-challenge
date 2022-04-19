package com.example.pathchallenge.core.cache.implementation

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pathchallenge.core.cache.DummyData
import com.example.pathchallenge.core.cache.abstraction.CharactersCache
import com.example.pathchallenge.core.cache.db.MarvelCharactersDb
import com.example.pathchallenge.core.cache.model.CharacterWithComics
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(
    AndroidJUnit4::class
)
class CharactersCacheImplTest {

    private lateinit var marvelDb: MarvelCharactersDb

    private lateinit var cache: CharactersCache

    @Before
    fun setup() {
        marvelDb = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MarvelCharactersDb::class.java
        )
            .allowMainThreadQueries().build()

        cache = CharactersCacheImpl(marvelDb.comicsDao)
    }


    @Test
    fun insertCharacterWithComics_insertsData_Successfully() {
        runBlocking {
            val comics = listOf(DummyData.comic)
            val character = DummyData.characterEntity
            val charWithComics = CharacterWithComics(comics = comics, characterEntity = character)
            Truth.assertThat(cache.getComics(character.id).first()).isEmpty()
            cache.insertCharacterWithComics(charWithComics)
            val newEntities = cache.getComics(character.id).first()
            Truth.assertThat(newEntities).isNotEmpty()
            Truth.assertThat(newEntities.first().comics.first().characterId).isEqualTo(character.id)
            Truth.assertThat(newEntities.first().comics.size).isEqualTo(comics.size)
        }
    }


    @After
    fun tearDown() {
        marvelDb.close()
    }
}