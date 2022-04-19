package com.example.pathchallenge.core.remote.model.comics

data class Comic(
    val id: Int,
    val thumbnail: Thumbnail,
    val title: String,
    val dates: List<Date>
)