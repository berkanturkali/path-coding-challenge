package com.example.pathchallenge.core.util

import com.example.pathchallenge.core.BuildConfig

object Constants {

    //Base url
    const val BASE_URL = BuildConfig.BASE_URL

    /* endpoints */
    const val characters = "/v1/public/characters"
    const val character_comics = "/v1/public/characters/{characterId}/comics"

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
}