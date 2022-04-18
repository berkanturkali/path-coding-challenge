package com.example.pathchallenge.core.remote.model.characters

data class CharactersResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: String,
    val copyright: String,
    val data: DataDto,
    val etag: String,
    val status: String
)