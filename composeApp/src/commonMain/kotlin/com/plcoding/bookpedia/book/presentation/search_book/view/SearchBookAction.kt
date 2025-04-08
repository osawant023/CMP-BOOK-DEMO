package com.plcoding.bookpedia.book.presentation.search_book.view

import com.plcoding.bookpedia.book.domain.model.Book

sealed interface SearchBookAction {

    data class OnSearchQueryChange(val queryChange:String) : SearchBookAction
    data class OnBookClick(val book: Book) : SearchBookAction
}