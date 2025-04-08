package com.plcoding.bookpedia.book.presentation.book_list.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.hint_search
import com.plcoding.bookpedia.core.presentation.utill.SolidWhite
import org.jetbrains.compose.resources.stringResource


@Composable
fun BookSearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    singleLine : Boolean = true,
    onKeyboardActions: KeyboardActions = KeyboardActions.Default,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchQuery,
        modifier = modifier
            .background(shape = RoundedCornerShape(100), color = SolidWhite),
        singleLine = singleLine,
        keyboardActions = onKeyboardActions,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        onValueChange = {
            onSearchQueryChange.invoke(it)
        },
        leadingIcon = {
            Icon(Icons.Default.Search , contentDescription = "Search")
        },
        trailingIcon = {
            AnimatedVisibility(visible = searchQuery.isNotBlank()){
                IconButton(
                    onClick = {
                        onSearchQueryChange("")
                    }
                ){
                    Icon(Icons.Default.Close , contentDescription = "Close")
                }
            }
        },
        shape = RoundedCornerShape(100),
        placeholder = {
            Text(stringResource(Res.string.hint_search))
        }
    )
}