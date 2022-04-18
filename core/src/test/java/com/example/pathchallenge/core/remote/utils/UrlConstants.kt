package com.example.pathchallenge.core.remote.utils

internal const val LIMIT = 30
internal const val OFFSET = 0
internal const val TS = "ts"
internal const val HASH = "hash"


//response
internal const val CHARACTERS_RESPONSE_PATH = "response/characters_response.json"
internal const val EMPTY_CHARACTERS_RESPONSE_PATH = "response/empty_characters_response.json"


//queries
internal const val CHARACTERS_QUERIES =
    "?offset=$OFFSET&limit=$LIMIT"

//paths
internal const val CHARACTERS_FULL_PATH = "$CHARACTERS_PATH$CHARACTERS_QUERIES"
internal const val EMPTY_CHARACTERS_PATH = "$CHARACTERS_PATH?offset=$OFFSET&limit=0"