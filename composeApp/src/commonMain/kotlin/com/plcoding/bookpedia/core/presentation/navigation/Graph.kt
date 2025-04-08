package com.plcoding.bookpedia.core.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Graph {
    @Serializable
    data object Store : Graph

    @Serializable
    data object Search : Graph

    @Serializable
    data object Favorites : Graph

    @Serializable
    data object Profile : Graph
}