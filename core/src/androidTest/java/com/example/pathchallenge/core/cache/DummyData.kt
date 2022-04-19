package com.example.pathchallenge.core.cache

import com.example.pathchallenge.core.cache.model.CharacterEntity
import com.example.pathchallenge.core.cache.model.ComicEntity

internal object DummyData {

    val characterEntity = CharacterEntity(1)

    val comic = ComicEntity(
        id = 1,
        thumbnail = "http://i.annihil.us/u/prod/marvel/i/mg/a/30/4e948fb5e9b52.jpg",
        title = "Avengers: The Initiative (2007) #14 (SPOTLIGHT VARIANT)",
        date = "2007-07-07",
        characterId = characterEntity.id
    )
}