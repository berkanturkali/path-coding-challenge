package com.example.pathchallenge.core.remote.model.characters

/**
 * Marvel API character network response item.
 *
 * @param id The unique ID of the character resource.
 * @param name The name of the character.
 * @param description A short bio or description of the character.
 * @param thumbnail The representative image for this character.
 * @param comics Comics of the character.
 */
data class CharacterDto(
    val comics: ComicsDto,
    val description: String,
    val id: Int,
    val modified: String,
    val name: String,
    val thumbnail: ThumbnailDto,
)