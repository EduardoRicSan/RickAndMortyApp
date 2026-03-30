package com.tech.data

import app.cash.turbine.test
import com.tech.core.network.NetworkResult
import com.tech.data.remote.api.RMApiService
import com.tech.data.remote.dataSource.RMDataSource
import com.tech.data.remote.dataSource.RMDataSourceImpl
import com.tech.data.remote.dto.response.CharacterWrapperResponseDTO
import com.tech.data.remote.dto.response.ResultsDTO
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RMDataSourceImplTest {

    private val apiService: RMApiService = mockk()
    private lateinit var dataSource: RMDataSource

    @Before
    fun setup() {
        dataSource = RMDataSourceImpl(apiService)
    }

    @Test
    fun `should emit Success when api call succeeds`() = runTest {
        val mockResponse = CharacterWrapperResponseDTO(
            results = listOf(ResultsDTO(id = 1, name = "Rick"))
        )

        coEvery { apiService.getAllCharacters(1) } returns mockResponse

        dataSource.getAllCharacters(1).test {

            val emissions = mutableListOf<NetworkResult<CharacterWrapperResponseDTO>>()

            repeat(2) {
                emissions.add(awaitItem())
            }

            val success = emissions.last()

            assert(success is NetworkResult.Success)
            assert((success as NetworkResult.Success).data.results.isNotEmpty())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should emit Error when api call fails`() = runTest {

        coEvery { apiService.getAllCharacters(1) } throws RuntimeException("API error")

        dataSource.getAllCharacters(1).test {

            val first = awaitItem()
            assert(first is NetworkResult.Loading)

            val second = awaitItem()
            assert(second is NetworkResult.Error)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should emit Loading then Success`() = runTest {
        val mockResponse = CharacterWrapperResponseDTO(
            results = listOf(ResultsDTO(id = 1, name = "Rick"))
        )

        coEvery { apiService.getAllCharacters(1) } returns mockResponse

        dataSource.getAllCharacters(1).test {

            val loading = awaitItem()
            assert(loading is NetworkResult.Loading)

            val success = awaitItem()
            assert(success is NetworkResult.Success)

            awaitComplete()
        }
    }

    @Test
    fun `should call api with correct page`() = runTest {

        coEvery { apiService.getAllCharacters(2) } returns CharacterWrapperResponseDTO()

        dataSource.getAllCharacters(2).test {
            awaitItem()
            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 1) {
            apiService.getAllCharacters(2)
        }
    }
}