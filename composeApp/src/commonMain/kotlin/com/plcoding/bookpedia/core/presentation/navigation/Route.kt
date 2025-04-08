package com.plcoding.bookpedia.core.presentation.navigation
import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Search : Route

    @Serializable
    data object Favourite : Route

    @Serializable
    data object Profile : Route

    @Serializable
    data object BookList : Route

    @Serializable
    data class BookDetails(val bookJson: String) : Route

}