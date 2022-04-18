package com.example.pathchallenge.core.remote.utils

import com.example.pathchallenge.core.util.Constants

internal const val API_KEY = Constants.PUBLIC_API_KEY

internal const val LIMIT = 30
internal const val OFFSET = 0
internal const val TS = "ts"
internal const val HASH = "hash"


//response
internal const val CHARACTERS_RESPONSE_PATH = "response/characters_response.json"


//queries
internal const val CHARACTERS_QUERIES =
    "?apiKey=$API_KEY&hash=$HASH&ts=$TS&offset=$OFFSET&limit=$LIMIT"

//paths
internal const val CHARACTERS_FULL_PATH = "$CHARACTERS_PATH$CHARACTERS_QUERIES"