package com.example.pathchallenge.extensions

import java.security.MessageDigest

internal fun String.toMD5() =
    MessageDigest
        .getInstance("MD5")
        .digest(toByteArray())
        .toHex()