package com.example.pathchallenge.core.remote.mapper.characters

import com.example.pathchallenge.core.domain.model.characters.Character
import com.example.pathchallenge.core.remote.model.characters.CharacterDto
import com.example.pathchallenge.core.remote.model.characters.ThumbnailDto
import com.example.pathchallenge.core.util.Constants.IMAGE_URL_FORMAT
import com.example.pathchallenge.mapper.RemoteModelMapper
import javax.inject.Inject

class CharacterMapper @Inject constructor() : RemoteModelMapper<CharacterDto, Character> {
    override fun mapFromModel(model: CharacterDto): Character {
        return Character(
            id = model.id,
            name = model.name,
            imageUrl =mapImageUrl(model.thumbnail),
            description = model.description
        )
    }

    internal fun mapImageUrl(thumbnailDto: ThumbnailDto): String {
        return IMAGE_URL_FORMAT.format(
            thumbnailDto.path.replace("http", "https"),
            thumbnailDto.extension
        )
    }
}