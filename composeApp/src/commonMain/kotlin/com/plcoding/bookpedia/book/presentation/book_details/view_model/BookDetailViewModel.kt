package com.plcoding.bookpedia.book.presentation.book_details.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.bookpedia.book.domain.model.Book
import com.plcoding.bookpedia.book.domain.repository.RepositoryBook
import com.plcoding.bookpedia.book.presentation.book_details.state.BookDetailState
import com.plcoding.bookpedia.book.presentation.book_details.view.BookDetailsAction
import com.plcoding.bookpedia.core.presentation.utill.ScreenState
import com.plcoding.bookpedia.core.presentation.utill.UiScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val repositoryBook: RepositoryBook
) : ViewModel() {

    fun init(book: Book){
        _state.update {
            it.copy(
                book = book
            )
        }
    }

    init {
        observeBook()
    }

    private val _state = MutableStateFlow(BookDetailState(
        book = null,
        uiState = UiScreenState(screenState = ScreenState.None),
        isFav = false
    ))
    val state = _state
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: BookDetailsAction) {
//        when (action) {
//            is BookDetailsAction.OnFavClick -> {
//                viewModelScope.launch {
//                    repositoryBook.toggleFav(action.book)
//                }
//            }
//
//            else -> {
//
//            }
//        }
    }

    private fun observeBook(){
//        repositoryBook
//            .isBookFav(state.value.book?.id ?: "")
//            .onEach { isFav ->
//                _state.update {
//                    it.copy(isFav = isFav)
//                }
//            }
//            .launchIn(viewModelScope)
    }

}