package com.example.pathchallenge.core.remote.model.characters

data class ComicsDto(
    val available: String,
    val collectionURI: String,
    val items: List<ItemDto>,
    val returned: String
)