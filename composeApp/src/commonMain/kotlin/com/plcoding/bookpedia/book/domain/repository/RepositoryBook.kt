package com.plcoding.bookpedia.book.domain.repository

import com.plcoding.bookpedia.book.domain.model.Book
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.EmptyResult
import com.plcoding.bookpedia.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface RepositoryBook {
    suspend fun fetchSearchBooks(
        query: String,
        resultLimit: Int?
    ): Result<List<Book>, DataError.Remote>

    suspend fun fetchBookList(
        resultLimit: Int?
    ): Result<List<Book>, DataError.Remote>

//    fun getFavBookList(): Flow<List<Book>>
//
//    fun isBookFav(id:String):Flow<Boolean>
//
//    suspend fun toggleFav(book: Book): EmptyResult<DataError.Local>

}