package com.example.pathchallenge.core.data.repository.implementation

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.pathchallenge.core.data.repository.abstraction.CharactersRepository
import com.example.pathchallenge.core.domain.model.characters.Character
import com.example.pathchallenge.core.remote.abstraction.CharactersRemote
import com.example.pathchallenge.core.remote.mapper.characters.CharacterMapper
import com.example.pathchallenge.core.remote.pagination.CharactersPagingSource
import com.example.pathchallenge.core.util.Constants.LIMIT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val characterMapper: CharacterMapper,
    private val charactersRemote: CharactersRemote
) : CharactersRepository {
    override fun fetchCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = LIMIT, enablePlaceholders = false),
            pagingSourceFactory = {
                CharactersPagingSource(
                    charactersRemote
                )
            }
        ).flow
            .map {
                it.map(characterMapper::mapFromModel)
            }
    }
}