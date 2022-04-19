package com.example.pathchallenge.core.util.extensions

internal fun ByteArray.toHex() = joinToString("") {
    "%02x".format(it)
}