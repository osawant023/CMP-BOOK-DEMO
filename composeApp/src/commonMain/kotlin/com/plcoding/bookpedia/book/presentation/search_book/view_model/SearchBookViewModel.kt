@file:OptIn(FlowPreview::class)

package com.plcoding.bookpedia.book.presentation.search_book.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.bookpedia.book.domain.model.Book
import com.plcoding.bookpedia.book.domain.repository.RepositoryBook
import com.plcoding.bookpedia.book.presentation.search_book.state.SearchBookState
import com.plcoding.bookpedia.book.presentation.search_book.view.SearchBookAction
import com.plcoding.bookpedia.core.domain.onError
import com.plcoding.bookpedia.core.domain.onSuccess
import com.plcoding.bookpedia.core.presentation.utill.ScreenState
import com.plcoding.bookpedia.core.presentation.utill.UiScreenState
import com.plcoding.bookpedia.core.presentation.utill.toUiText
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchBookViewModel(
    private val repositoryBook: RepositoryBook
) : ViewModel() {

    private var cachedBookList = emptyList<Book>()
    private var jobSearchBook: Job? = null

    private val _state = MutableStateFlow(SearchBookState())
    val state = _state
        .onStart {
            if (cachedBookList.isEmpty()) {
                observeSearchQuery()
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )


    fun onAction(action: SearchBookAction) {
        when (action) {
            is SearchBookAction.OnBookClick -> {

            }

            is SearchBookAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.queryChange)
                }
            }
        }
    }

    private fun observeSearchQuery() {
        state
            .map {
                it.searchQuery
            }.distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update {
                            it.copy(
                                uiState = it.uiState.copy(screenState = ScreenState.None),
                                searchResults = cachedBookList
                            )
                        }
                    }

                    query.length >= 2 -> {
                        jobSearchBook?.cancel()
                        jobSearchBook = searchBooks(query)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun searchBooks(query: String) = viewModelScope.launch {
        _state.update { it.copy(uiState = it.uiState.copy(screenState = ScreenState.Searching)) }
        repositoryBook.fetchSearchBooks(query, 20)
            .onSuccess { result ->
                cachedBookList = result
                _state.update {
                    it.copy(
                        searchResults = result,
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
                        searchResults = emptyList(),
                        uiState = UiScreenState(
                            screenState = ScreenState.None,
                            error = error.toUiText()
                        )
                    )
                }
            }
    }
}