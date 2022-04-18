package com.example.pathchallenge.core.util

import com.example.pathchallenge.core.BuildConfig

object Constants {

    //Base url
    const val BASE_URL = BuildConfig.BASE_URL

    /* endpoints */
    const val characters = "/v1/public/characters"

    //load size
    const val LIMIT = 30

    const val IMAGE_URL_FORMAT = "%s.%s"

}