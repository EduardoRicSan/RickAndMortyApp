package com.tech.core.route

import kotlinx.serialization.Serializable

// Navigation destinations used across the RM app
@Serializable
sealed interface RMAppDestination

// Search artist main screen destination
@Serializable
data object Characters : RMAppDestination
@Serializable
data object AboutApp : RMAppDestination

fun RMAppDestination.route(): String = this::class.qualifiedName!!
