package com.plcoding.bookpedia.book.presentation.book_details.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.book_sample
import coil3.compose.rememberAsyncImagePainter
import com.plcoding.bookpedia.LocalNavController
import com.plcoding.bookpedia.book.domain.model.Book
import com.plcoding.bookpedia.book.presentation.book_details.state.BookDetailState
import com.plcoding.bookpedia.core.presentation.components.CustomToolbar
import com.plcoding.bookpedia.core.presentation.utill.SandYellow
import com.plcoding.bookpedia.core.presentation.utill.SolidWhite
import org.jetbrains.compose.resources.painterResource
import kotlin.math.round

@Composable
fun BookDetailsScreenRoot(
    book: Book,
) {
    val navController = LocalNavController.current
    val uiState by mutableStateOf(BookDetailState(book = book))
    BookDetailsScreen(uiState) { action ->
        when (action) {
            BookDetailsAction.OnBackClick -> navController.navigateUp()
            else ->{

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailsScreen(
    uiState: BookDetailState, onAction: (BookDetailsAction) -> Unit
) {
    var heightOfTopContainer by remember { mutableStateOf(0) }

    var imageLoadResult by remember {
        mutableStateOf<Result<Painter>?>(null)
    }
    val painter = rememberAsyncImagePainter(model = uiState.book?.imageUrl, onSuccess = {
        imageLoadResult =
            if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                Result.success(it.painter)
            } else {
                Result.failure(Exception("Invalid image size"))
            }
    }, onError = {
        it.result.throwable.printStackTrace()
        imageLoadResult = Result.failure(it.result.throwable)
    })

    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.TopStart
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(top = with(LocalDensity.current) { (heightOfTopContainer).toDp() }),
            contentAlignment = Alignment.TopStart
        ) {

            Image(
                modifier = Modifier.fillMaxSize().blur(30.dp),
                painter = if (imageLoadResult?.isSuccess == true) painter else {
                    painterResource(Res.drawable.book_sample)
                },
                contentDescription = "",
                contentScale = ContentScale.Crop
            )


            Column(
                modifier = Modifier.padding(
                    top = with(LocalDensity.current) { heightOfTopContainer.toDp() },
                    start = 30.dp,
                    end = 30.dp
                )
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                uiState.book?.description?.let { description ->
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        color = SolidWhite,
                    )

                    Spacer(Modifier.padding(top = 10.dp))

                    Text(
                        text = description,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        color = SolidWhite,
                        fontWeight = FontWeight.SemiBold,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                uiState.book?.languages?.joinToString(",").let { lang ->
                    Spacer(Modifier.padding(top = 50.dp))
                    Text(
                        text = "Languages",
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        color = SolidWhite,
                    )
                    Spacer(Modifier.padding(top = 10.dp))
                    Text(
                        text = lang ?: "",
                        style = MaterialTheme.typography.bodyLarge,
                        color = SolidWhite,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                uiState.book?.numPages?.let { numPage ->
                    Spacer(Modifier.padding(top = 50.dp))
                    Text(
                        text = "Pages",
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        color = SolidWhite,
                    )
                    Spacer(Modifier.padding(top = 10.dp))
                    Text(
                        text = numPage.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = SolidWhite,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        Box(
            modifier = Modifier.wrapContentHeight()
        ) {
            Column(modifier = Modifier
                .padding(bottom = 40.dp)
                .wrapContentHeight()
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp)
                ).padding(bottom = 70.dp).onGloballyPositioned {
                    heightOfTopContainer = it.size.height - 70
                }) {
                CustomToolbar(
                    title = "Book Details",
                    topAppBarColors = TopAppBarDefaults.topAppBarColors().copy(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    isBackArrow = true
                ) {
                    onAction.invoke(BookDetailsAction.OnBackClick)
                }
                Row(
                    modifier = Modifier.padding(horizontal = 35.dp).padding(top = 35.dp)
                        .fillMaxWidth().height(IntrinsicSize.Min),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier.height(100.dp), contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier.aspectRatio(
                                0.65f, matchHeightConstraintsFirst = true
                            ).background(color = MaterialTheme.colorScheme.primary),
                            painter = if (imageLoadResult?.isSuccess == true) painter else {
                                painterResource(Res.drawable.book_sample)
                            },
                            contentScale = ContentScale.Crop,
                            contentDescription = ""
                        )
                    }

                    Column(
                        modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = uiState.book?.title ?: "",
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 2,
                            color = MaterialTheme.colorScheme.onPrimary,
                            overflow = TextOverflow.Ellipsis
                        )

                        uiState.book?.averageRating?.let { rating ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${round(rating * 10) / 10.0}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                )
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = SandYellow
                                )
                            }
                        }
                    }

                    Column(
                        modifier = Modifier.width(50.dp).fillMaxHeight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier.size(40.dp),
                            imageVector = if (uiState.isFav == true)
                                Icons.Outlined.Favorite
                            else
                                Icons.Filled.Favorite,
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
                        )
                    }
                }
            }

            Card(
                modifier = Modifier.padding(horizontal = 50.dp).align(Alignment.BottomCenter),
                elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                shape = RoundedCornerShape(30),
                colors = CardDefaults.cardColors()
                    .copy(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier.padding(10.dp).fillMaxWidth().height(IntrinsicSize.Min),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Author",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        uiState.book?.authors?.firstOrNull()?.let { authorName ->
                            Text(
                                text = authorName,
                                style = MaterialTheme.typography.bodyLarge,
                                maxLines = 1,
                                fontWeight = FontWeight.SemiBold,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }
                }
            }
        }
    }
}
