package com.tech.design_system.common.model

enum class CharacterStatus {
    ALIVE, DEAD, UNKNOWN;

    companion object {
        fun from(value: String?): CharacterStatus {
            return when (value?.lowercase()) {
                "alive" -> ALIVE
                "dead" -> DEAD
                else -> UNKNOWN
            }
        }
    }
}