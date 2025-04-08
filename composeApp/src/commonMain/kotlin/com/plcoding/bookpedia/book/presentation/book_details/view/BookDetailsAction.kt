package com.plcoding.bookpedia.book.presentation.book_details.view

import com.plcoding.bookpedia.book.domain.model.Book

sealed interface BookDetailsAction {

    data object OnBackClick : BookDetailsAction

    data class OnFavClick(val book: Book):BookDetailsAction
}