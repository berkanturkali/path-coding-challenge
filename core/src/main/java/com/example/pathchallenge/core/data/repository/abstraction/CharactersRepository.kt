package com.example.pathchallenge.core.data.repository.abstraction

import androidx.paging.PagingData
import com.example.pathchallenge.core.domain.model.characters.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun fetchCharacters(): Flow<PagingData<Character>>
}