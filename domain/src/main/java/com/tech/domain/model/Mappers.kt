package com.tech.domain.model

import com.tech.data.remote.dto.response.CharacterWrapperResponseDTO
import com.tech.data.remote.dto.response.InfoDTO
import com.tech.data.remote.dto.response.LocationDTO
import com.tech.data.remote.dto.response.OriginDTO
import com.tech.data.remote.dto.response.ResultsDTO

fun CharacterWrapperResponseDTO.toDomain(): CharacterWrapper {
    return CharacterWrapper(
        info = info?.toDomainModel() ?: Info.empty(),
        results = results.map { it.toDomain() }
    )
}

fun InfoDTO.toDomainModel(): Info {
    return Info(
        count = count ?: 0,
        pages = pages ?: 0,
        next = next,
        prev = prev
    )
}

fun ResultsDTO.toDomain(): CharacterModel {
    return CharacterModel(
        id = id ?: -1,
        name = name.orEmpty(),
        status = status.orEmpty(),
        species = species.orEmpty(),
        type = type.orEmpty(),
        gender = gender.orEmpty(),
        origin = origin?.name,
        location = location?.name,
        image = image.orEmpty(),
        episode = episode,
        url = url.orEmpty(),
        created = created.orEmpty()
    )
}

fun OriginDTO.toDomainModel(): Origin {
    return Origin(
        name = name.orEmpty(),
        url = url.orEmpty()
    )
}

fun LocationDTO.toDomainModel(): Location {
    return Location(
        name = name.orEmpty(),
        url = url.orEmpty()
    )
}