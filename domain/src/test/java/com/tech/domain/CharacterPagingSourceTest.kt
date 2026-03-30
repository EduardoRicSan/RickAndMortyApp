package com.tech.domain

import androidx.paging.PagingSource
import com.tech.data.remote.api.RMApiService
import com.tech.data.remote.dto.response.CharacterWrapperResponseDTO
import com.tech.data.remote.dto.response.InfoDTO
import com.tech.data.remote.dto.response.ResultsDTO
import com.tech.domain.paging.CharacterPagingSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterPagingSourceTest {

    private val apiService: RMApiService = mockk()
    private lateinit var pagingSource: CharacterPagingSource

    @Before
    fun setup() {
        pagingSource = CharacterPagingSource(apiService)
    }

    @Test
    fun `load returns Page when API is successful`() = runTest {
        val fakeResponse = CharacterWrapperResponseDTO(
            info = InfoDTO(next = "next"),
            results = listOf(
                ResultsDTO(id = 1, name = "Rick")
            )
        )

        coEvery { apiService.getAllCharacters(1) } returns fakeResponse

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        assert(result is PagingSource.LoadResult.Page)

        val page = result as PagingSource.LoadResult.Page
        assert(page.data.size == 1)
        assert(page.nextKey == 2)
        assert(page.prevKey == null)

        coVerify { apiService.getAllCharacters(1) }
    }

    @Test
    fun `load returns Error when API fails`() = runTest {
        coEvery { apiService.getAllCharacters(1) } throws RuntimeException("Error")

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        assert(result is PagingSource.LoadResult.Error)
    }
}