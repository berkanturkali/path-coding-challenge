package com.example.pathchallenge.core.domain.model.characters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val imageUrl: String,
) : Parcelable