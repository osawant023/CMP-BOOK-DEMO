package com.plcoding.bookpedia

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.bookpedia.book.presentation.book_details.state.BookDetailState
import com.plcoding.bookpedia.book.presentation.book_details.view.BookDetailsScreen
import com.plcoding.bookpedia.book.presentation.book_list.state.dummyBooks

@Preview
@Composable
private fun BookListScreenPreview(){
    MaterialTheme {
        BookDetailsScreen(
            BookDetailState(dummyBooks.first())
        ){

        }
    }
}