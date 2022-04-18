package com.example.pathchallenge.core.remote.pagination

import androidx.paging.PagingSource
import com.example.pathchallenge.core.remote.abstraction.CharactersRemote
import com.example.pathchallenge.core.remote.implementation.CharactersRemoteImpl
import com.example.pathchallenge.core.remote.utils.LIMIT
import com.example.pathchallenge.core.remote.utils.OFFSET
import com.example.pathchallenge.core.remote.utils.RequestDispatcher
import com.example.pathchallenge.core.remote.utils.makeApiService
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class CharactersPagingSourceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var charactersRemote: CharactersRemote

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = RequestDispatcher()
        mockWebServer.start()
        charactersRemote = CharactersRemoteImpl(makeApiService(mockWebServer))
    }

    @Test
    fun `check that prev and next keys are correct`() = runBlocking {
        val pagingSource = CharactersPagingSource(charactersRemote)
        val characters = charactersRemote.fetchCharacters(0, 30)
        Truth.assertThat(
            PagingSource.LoadResult.Page(
                data = characters.data.results,
                prevKey = null,
                nextKey = 30
            )
        ).isEqualTo(
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 30,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `check that prev and next keys are correct when there is no data`() =
        runBlocking {
            val pagingSource = CharactersPagingSource(charactersRemote)
            Truth.assertThat(
                PagingSource.LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            ).isEqualTo(
                pagingSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 0,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `check that paging source returns error when error occurs`() = runBlocking {
        val charactersRemote = mockk<CharactersRemote>()
        coEvery { charactersRemote.fetchCharacters(OFFSET, LIMIT) } throws Exception()
        val pagingSource = CharactersPagingSource(charactersRemote)

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = LIMIT,
                placeholdersEnabled = false
            )
        )
        Truth.assertThat(result).isInstanceOf(PagingSource.LoadResult.Error::class.java)
        Truth.assertThat((result as PagingSource.LoadResult.Error).throwable.message)
            .isEqualTo("Something went wrong.")
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}