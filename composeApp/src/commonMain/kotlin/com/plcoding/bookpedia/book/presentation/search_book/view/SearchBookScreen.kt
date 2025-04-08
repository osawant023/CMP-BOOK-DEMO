package com.plcoding.bookpedia.book.presentation.search_book.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.no_search_results
import com.plcoding.bookpedia.LocalNavController
import com.plcoding.bookpedia.book.domain.model.toJsonString
import com.plcoding.bookpedia.book.presentation.book_list.component.BookList
import com.plcoding.bookpedia.book.presentation.book_list.component.BookSearchBar
import com.plcoding.bookpedia.book.presentation.book_list.view.BookListAction
import com.plcoding.bookpedia.book.presentation.search_book.state.SearchBookState
import com.plcoding.bookpedia.book.presentation.search_book.view_model.SearchBookViewModel
import com.plcoding.bookpedia.core.presentation.utill.ScreenState
import com.plcoding.bookpedia.core.presentation.components.CustomToolbar
import com.plcoding.bookpedia.core.presentation.navigation.Route
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchBookScreenRoot(
    viewModel: SearchBookViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val navController = LocalNavController.current
    SearchBookScreen(
        modifier = modifier,
        state = state
    ) { action ->
        when (action) {
            is SearchBookAction.OnBookClick -> {
                navController.navigate(Route.BookDetails(action.book.toJsonString()))
            }
            else -> viewModel.onAction(action)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBookScreen(
    modifier: Modifier = Modifier,
    state: SearchBookState,
    onAction: (SearchBookAction) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomToolbar("Search")
        BookSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = {
                onAction(SearchBookAction.OnSearchQueryChange(it))
            },
            onKeyboardActions = KeyboardActions {
                keyboardController?.hide()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                if(state.uiState.screenState != ScreenState.None) {
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
                        state.searchResults.isEmpty() -> {
                            Text(
                                text = stringResource(Res.string.no_search_results),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                        else -> {
                            BookList(
                                books = state.searchResults,
                                onBookClick = {
                                    onAction(SearchBookAction.OnBookClick(it))
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
