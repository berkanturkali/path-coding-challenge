package com.example.pathchallenge.core.cache.mapper

import com.example.pathchallenge.core.cache.mapper.base.EntityMapper
import com.example.pathchallenge.core.cache.model.CharacterEntity
import com.example.pathchallenge.core.domain.model.characters.Character
import javax.inject.Inject

class CharacterEntityMapper @Inject constructor() : EntityMapper<CharacterEntity?, Character> {
    override fun mapFromEntity(entity: CharacterEntity?): Character {
        return entity?.let {
            return Character(
                id = entity.id,
                name = entity.name,
                imageUrl = entity.imageUrl,
                description = entity.description
            )
        } ?: Character(id = -1, name = "", imageUrl = "", description = "")
    }

    override fun mapToEntity(type: Character): CharacterEntity {
        return CharacterEntity(
            id = type.id,
            name = type.name,
            imageUrl = type.imageUrl,
            description = type.description
        )
    }
}