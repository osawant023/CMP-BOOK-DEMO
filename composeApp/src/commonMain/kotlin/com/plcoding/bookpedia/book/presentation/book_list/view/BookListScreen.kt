package com.plcoding.bookpedia.book.presentation.book_list.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.no_search_results
import com.plcoding.bookpedia.LocalNavController
import com.plcoding.bookpedia.book.domain.model.toJsonString
import com.plcoding.bookpedia.book.presentation.book_list.component.BookList
import com.plcoding.bookpedia.book.presentation.book_list.state.BookListState
import com.plcoding.bookpedia.book.presentation.book_list.view_model.BookListViewModel
import com.plcoding.bookpedia.core.presentation.utill.ScreenState
import com.plcoding.bookpedia.core.presentation.components.CustomToolbar
import com.plcoding.bookpedia.core.presentation.navigation.Route
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BookListScreenRoot(
    viewModel: BookListViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val navController = LocalNavController.current
    BookListScreen(
        modifier = modifier,
        state = state
    ) { action ->
        when (action) {
            is BookListAction.OnBookClick -> {
                navController.navigate(Route.BookDetails(action.book.toJsonString()))
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(
    modifier: Modifier = Modifier,
    state: BookListState,
    onAction: (BookListAction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomToolbar("Store")
        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (state.uiState.screenState != ScreenState.None) {
                    CircularProgressIndicator()
                } else {
                    when {
                        state.uiState.error != null -> {
                            Text(
                                text = state.uiState.error.asString(),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        state.listBooks.isEmpty() -> {
                            Text(
                                text = stringResource(Res.string.no_search_results),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        else -> {
                            BookList(
                                books = state.listBooks,
                                onBookClick = {
                                    onAction(BookListAction.OnBookClick(it))
                                },
                                modifier = Modifier.fillMaxSize(),
                            )
                        }
                    }
                }
            }

        }
    }
}
