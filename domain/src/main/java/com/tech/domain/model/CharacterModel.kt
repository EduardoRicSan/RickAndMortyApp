package com.tech.domain.model


data class CharacterModel(
    var id : Int?              = null,
    var name     : String?           = null,
    var species  : String?           = null,
    var status   : String?           = null,
    var type     : String?           = null,
    var gender   : String?           = null,
    var image    : String?           = null,
    var episode  : List<String> = listOf(),
    var url      : String?           = null,
    var created  : String?           = null,
    val origin   : String? = null,
    val location : String? = null,
)



