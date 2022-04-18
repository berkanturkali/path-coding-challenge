package com.example.pathchallenge.core.remote.model.characters

data class CharacterDto(
    val comics: ComicsDto,
    val description: String,
    val id: String,
    val modified: String,
    val name: String,
    val thumbnail: ThumbnailDto,
    val urls: List<UrlDto>
)