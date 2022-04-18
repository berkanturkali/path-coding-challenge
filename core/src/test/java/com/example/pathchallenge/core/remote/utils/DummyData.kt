package com.example.pathchallenge.core.remote.utils

import com.example.pathchallenge.core.remote.model.characters.CharacterDto
import com.example.pathchallenge.core.remote.model.characters.ThumbnailDto

internal object DummyData {

    //network character model
    val characterDto = CharacterDto(
        id = 1011334,
        name = "3-D Man",
        description = "",
        thumbnail = ThumbnailDto(
            path = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
            extension = "jpg"
        )
    )
}