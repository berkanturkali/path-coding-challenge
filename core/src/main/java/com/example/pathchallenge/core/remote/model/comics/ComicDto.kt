package com.example.pathchallenge.core.remote.model.comics

data class ComicDto(
    val id: Int,
    val thumbnail: Thumbnail,
    val title: String,
    val dates: List<Date>
)