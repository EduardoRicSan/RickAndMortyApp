package com.tech.domain.model


data class CharacterWrapper(
    val info: Info,
    val results: List<CharacterModel>
)

data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
) {
    companion object {
        fun empty() = Info(0, 0, null, null)
    }
}
data class Origin(
    val name: String,
    val url: String
) {
    companion object {
        fun empty() = Origin("", "")
    }
}
data class Location(
    val name: String,
    val url: String
) {
    companion object {
        fun empty() = Location("", "")
    }
}