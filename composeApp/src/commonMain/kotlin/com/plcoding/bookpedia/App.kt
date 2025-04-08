package com.plcoding.bookpedia

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.plcoding.bookpedia.book.domain.model.Book
import com.plcoding.bookpedia.book.presentation.book_details.view.BookDetailsScreenRoot
import com.plcoding.bookpedia.book.presentation.book_list.view.BookListScreenRoot
import com.plcoding.bookpedia.book.presentation.search_book.view.SearchBookScreenRoot
import com.plcoding.bookpedia.core.presentation.navigation.Graph
import com.plcoding.bookpedia.core.presentation.navigation.Route
import com.plcoding.bookpedia.core.presentation.navigation.TopLevelRoute
import com.plcoding.bookpedia.core.presentation.utill.MyTheme
import kotlinx.serialization.json.Json

val topLevelRoutes = listOf(
    TopLevelRoute("Store", Graph.Store, Icons.Filled.Home),
    TopLevelRoute("Search", Graph.Search, Icons.Filled.Search),
    TopLevelRoute("Favorites", Graph.Favorites, Icons.Filled.Favorite),
    //TopLevelRoute("Profile", Graph.Profile, Icons.Filled.AccountBox)
)

val LocalNavController =
    compositionLocalOf<NavHostController> { error("CompositionLocal LocalNavController not present") }

@Composable
fun App() {
    MyTheme {
        val navController = rememberNavController()
        CompositionLocalProvider(
            LocalNavController provides navController
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    NavigationBar(
                        modifier = Modifier
                            .wrapContentSize()
                            .shadow(10.dp),
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.surface
                    ) {
                        topLevelRoutes.forEach { topLevelRoute ->
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        topLevelRoute.icon,
                                        contentDescription = topLevelRoute.name,
                                    )
                                },
                                label = { Text(topLevelRoute.name) },
                                selected = currentDestination?.hierarchy?.any {
                                    it.hasRoute(
                                        topLevelRoute.route::class,
                                    )
                                } == true,
                                onClick = {
                                    navController.navigate(topLevelRoute.route) {
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a large stack of destinations
                                        // on the back stack as users select items
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // re-selecting the same item
                                        launchSingleTop = true
                                        // Restore state when reselecting a previously selected item
                                        restoreState = true
                                    }
                                },
                                colors = NavigationBarItemDefaults.colors().copy(
                                    selectedIconColor = MaterialTheme.colorScheme.onSurface,
                                    selectedTextColor = MaterialTheme.colorScheme.onSurface,
                                    selectedIndicatorColor = MaterialTheme.colorScheme.secondary,
                                    unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                    unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                )
                            )
                        }
                    }
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    startDestination = Graph.Store
                ) {
                    navigation<Graph.Store>(startDestination = Route.BookList) {
                        allComposable()
                    }

                    navigation<Graph.Search>(startDestination = Route.Search) {
                        allComposable()
                    }

                    navigation<Graph.Favorites>(startDestination = Route.Favourite) {
                        allComposable()
                    }

                    navigation<Graph.Profile>(startDestination = Route.Profile) {
                        allComposable()
                    }

                    allComposable()
                }
            }
        }
    }
}

private fun NavGraphBuilder.allComposable() {

    composable<Route.BookList> {
        BookListScreenRoot()
    }

    composable<Route.Search> {
        SearchBookScreenRoot()
    }

    composable<Route.Favourite> {
        val route = it.toRoute<Route.Favourite>()

    }

    composable<Route.Profile> {
        val route = it.toRoute<Route.Profile>()

    }

    composable<Route.BookDetails> {
        val route = it.toRoute<Route.BookDetails>()
        val bookDetails : Book = Json.decodeFromString(route.bookJson)
        BookDetailsScreenRoot(bookDetails)
    }


}