package com.example.pathchallenge.extensions

internal fun ByteArray.toHex() = joinToString("") {
    "%02x".format(it)
}