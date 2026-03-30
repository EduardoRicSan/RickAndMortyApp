package com.tech.data

import com.tech.data.remote.api.RMApiService
import com.tech.data.remote.api.RMApiServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RMApiServiceImplTest {

    private lateinit var apiService: RMApiService

    @Test
    fun `should return characters when api call is successful`() = runTest {
        val mockEngine = MockEngine { request ->
            respond(
                content = """
                    {
                        "info": {
                            "count": 1,
                            "pages": 1,
                            "next": null,
                            "prev": null
                        },
                        "results": [
                            {
                                "id": 1,
                                "name": "Rick Sanchez",
                                "status": "Alive",
                                "species": "Human",
                                "gender": "Male",
                                "image": "url"
                            }
                        ]
                    }
                """.trimIndent(),
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json()
            }
        }

        apiService = RMApiServiceImpl(client)

        val result = apiService.getAllCharacters(page = 1)

        assert(result.results.isNotEmpty())
        assert(result.results.first().name == "Rick Sanchez")
    }
}