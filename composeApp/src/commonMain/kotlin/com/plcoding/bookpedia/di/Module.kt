package com.plcoding.bookpedia.di

import com.plcoding.bookpedia.book.data.remote.source.KtorRemoteBookListDataSource
import com.plcoding.bookpedia.book.data.remote.source.RemoteSourceBookList
import com.plcoding.bookpedia.book.data.repository.DataSourceBookRepository
import com.plcoding.bookpedia.book.domain.repository.RepositoryBook
import com.plcoding.bookpedia.book.presentation.book_list.view_model.BookListViewModel
import com.plcoding.bookpedia.book.presentation.search_book.view_model.SearchBookViewModel
import com.plcoding.bookpedia.core.data.remote.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule : Module

val sharedModule = module{
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteBookListDataSource).bind<RemoteSourceBookList>()
    singleOf(::DataSourceBookRepository).bind<RepositoryBook>()

//    single {
//        get<AppDataBaseFactory>()
//            .create()
//            .setDriver(BundledSQLiteDriver())
//            .build()
//    }
//
//    single { get<AppDataBase>().favouriteBookDao }

    viewModelOf(::BookListViewModel)
    //viewModelOf(::BookDetailViewModel)
    viewModelOf(::SearchBookViewModel)
}