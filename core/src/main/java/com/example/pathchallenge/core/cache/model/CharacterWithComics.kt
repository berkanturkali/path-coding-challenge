package com.example.pathchallenge.core.cache.model

import androidx.room.Embedded
import androidx.room.Relation

data class CharacterWithComics(
    @Embedded val characterEntity: CharacterEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "characterId"
    )
    val comics: List<ComicEntity>
)