@file:OptIn(FlowPreview::class)

package com.plcoding.bookpedia.book.presentation.book_list.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.bookpedia.book.domain.model.Book
import com.plcoding.bookpedia.book.domain.repository.RepositoryBook
import com.plcoding.bookpedia.book.presentation.book_list.state.BookListState
import com.plcoding.bookpedia.book.presentation.book_list.view.BookListAction
import com.plcoding.bookpedia.core.domain.onError
import com.plcoding.bookpedia.core.domain.onSuccess
import com.plcoding.bookpedia.core.presentation.utill.ScreenState
import com.plcoding.bookpedia.core.presentation.utill.UiScreenState
import com.plcoding.bookpedia.core.presentation.utill.toUiText
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookListViewModel(
    private val repositoryBook: RepositoryBook
) : ViewModel() {

    private var cachedBookList = emptyList<Book>()

    private val _state = MutableStateFlow(BookListState())
    val state = _state
        .onStart {
            if (_state.value.listBooks.isEmpty()) {
                getBookList()
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )


    fun onAction(action: BookListAction) {
        when (action) {
            is BookListAction.OnBookClick -> {

            }
        }
    }


    private fun getBookList() = viewModelScope.launch {
        _state.update { it.copy(uiState = it.uiState.copy(screenState = ScreenState.Searching)) }
        repositoryBook.fetchBookList(20)
            .onSuccess { result ->
                cachedBookList = result
                _state.update {
                    it.copy(
                        listBooks = result,
                        uiState = it.uiState.copy(
                            screenState = ScreenState.None,
                            error = null
                        ),
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        listBooks = emptyList(),
                        uiState = UiScreenState(
                            screenState = ScreenState.None,
                            error = error.toUiText()
                        )
                    )
                }
            }
    }
}