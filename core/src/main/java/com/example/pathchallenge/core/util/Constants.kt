package com.example.pathchallenge.core.util

import com.example.pathchallenge.core.BuildConfig

object Constants {

    //Base url
    const val BASE_URL = BuildConfig.BASE_URL

    /* endpoints */
    const val CHARACTERS = "/v1/public/characters"
    const val CHARACTERS_COMICS = "$CHARACTERS/{characterId}/comics"

    //load size
    const val LIMIT = 30

    const val IMAGE_URL_FORMAT = "%s.%s"

    const val DATE_RANGE_FORMAT = "%s,%s"

    const val START_DATE = "2005-01-01"
    const val END_DATE = "2022-01-01"
    val DATE_RANGE = DATE_RANGE_FORMAT.format(
        START_DATE,
        END_DATE
    )

    //api keys
    // these should be hidden
    internal const val PUBLIC_API_KEY = "c20a9a12ecaced19d4661656225a014d"
    internal const val PRIVATE_API_KEY = "e3e36a2b2ad77a4266c2cfa54818b91ca734b2c9"

    internal const val HASH_FORMAT = "%s%s%s"
}