package com.plcoding.bookpedia.book.presentation.book_details.state

import com.plcoding.bookpedia.book.domain.model.Book
import com.plcoding.bookpedia.core.presentation.utill.UiScreenState

data class BookDetailState(
    val book:Book? = null,
    val uiState:UiScreenState = UiScreenState(),
    val isFav:Boolean ?= false,
)
