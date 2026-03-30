package com.tech.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class
CharacterWrapperResponseDTO(
    @SerialName("info"    ) var info    : InfoDTO?              = InfoDTO(),
    @SerialName("results" ) var results : List<ResultsDTO> = listOf()
)
@Serializable
data class InfoDTO(
    @SerialName("count" ) var count : Int?    = null,
    @SerialName("pages" ) var pages : Int?    = null,
    @SerialName("next"  ) var next  : String? = null,
    @SerialName("prev"  ) var prev  : String? = null,
    )

@Serializable
data class ResultsDTO(
    @SerialName("id"       ) var id       : Int?              = null,
    @SerialName("name"     ) var name     : String?           = null,
    @SerialName("status"   ) var status   : String?           = null,
    @SerialName("species"  ) var species  : String?           = null,
    @SerialName("type"     ) var type     : String?           = null,
    @SerialName("gender"   ) var gender   : String?           = null,
    @SerialName("origin"   ) var origin   : OriginDTO?           = OriginDTO(),
    @SerialName("location" ) var location : LocationDTO?         = LocationDTO(),
    @SerialName("image"    ) var image    : String?           = null,
    @SerialName("episode"  ) var episode  : List<String> = listOf(),
    @SerialName("url"      ) var url      : String?           = null,
    @SerialName("created"  ) var created  : String?           = null
)
@Serializable
data class OriginDTO (
    @SerialName("name" ) var name : String? = null,
    @SerialName("url"  ) var url  : String? = null

)

@Serializable
data class LocationDTO (
    @SerialName("name" ) var name : String? = null,
    @SerialName("url"  ) var url  : String? = null

)




