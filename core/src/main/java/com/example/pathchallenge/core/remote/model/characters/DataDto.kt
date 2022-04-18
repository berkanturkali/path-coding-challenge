package com.example.pathchallenge.core.remote.model.characters

data class DataDto(
    val count: String,
    val limit: String,
    val offset: String,
    val results: List<CharacterDto>,
    val total: String
)