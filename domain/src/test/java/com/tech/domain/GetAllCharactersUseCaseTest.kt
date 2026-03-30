package com.tech.domain

import androidx.paging.PagingData
import app.cash.turbine.test
import com.tech.domain.model.CharacterModel
import com.tech.domain.repository.RMRepository
import com.tech.domain.useCase.GetAllCharactersUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetAllCharactersUseCaseTest {

    private val repository: RMRepository = mockk()
    private lateinit var useCase: GetAllCharactersUseCase

    @Before
    fun setup() {
        useCase = GetAllCharactersUseCase(repository)
    }

    @Test
    fun `invoke should return flow from repository`() = runTest {
        val fakePagingData = PagingData.from(listOf(CharacterModel(id = 1)))
        val fakeFlow = flowOf(fakePagingData)

        every { repository.getAllCharacters() } returns fakeFlow

        val result = useCase()

        result.test {
            val emission = awaitItem()
            assert(emission != null)
            cancelAndIgnoreRemainingEvents()
        }

        verify(exactly = 1) { repository.getAllCharacters() }
    }
}