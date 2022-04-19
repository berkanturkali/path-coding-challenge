package com.example.pathchallenge.core.cache.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "comics", foreignKeys = [ForeignKey(
        entity = CharacterEntity::class,
        parentColumns = ["id"],
        childColumns = ["characterId"],
        onDelete = CASCADE
    )]
)
data class ComicEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val thumbnail: String,
    val title: String,
    val date: String,
    val characterId: Int
)