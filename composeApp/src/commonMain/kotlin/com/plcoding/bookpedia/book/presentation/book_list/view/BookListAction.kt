package com.plcoding.bookpedia.book.presentation.book_list.view

import com.plcoding.bookpedia.book.domain.model.Book

sealed interface BookListAction {

    data class OnBookClick(val book: Book) : BookListAction
}