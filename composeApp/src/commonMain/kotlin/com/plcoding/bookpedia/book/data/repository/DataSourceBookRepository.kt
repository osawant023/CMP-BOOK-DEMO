package com.plcoding.bookpedia.book.data.repository

import androidx.sqlite.SQLiteException
import com.plcoding.bookpedia.book.data.local_database.AppDataBase
import com.plcoding.bookpedia.book.data.local_database.dao.BookDao
import com.plcoding.bookpedia.book.data.remote.source.KtorRemoteBookListDataSource
import com.plcoding.bookpedia.book.domain.model.Book
import com.plcoding.bookpedia.book.domain.repository.RepositoryBook
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.EmptyResult
import com.plcoding.bookpedia.core.domain.Result
import com.plcoding.bookpedia.core.domain.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataSourceBookRepository(
    private val remoteBookListDataSource: KtorRemoteBookListDataSource,
//    private val daoBook: BookDao
) : RepositoryBook {
    override suspend fun fetchSearchBooks(
        query: String,
        resultLimit: Int?
    ): Result<List<Book>, DataError.Remote> {
        return remoteBookListDataSource.fetchSearchBooks(query, resultLimit)
            .map {
                it.docs?.map { dtoSearchBook -> dtoSearchBook.toBook() } ?: emptyList()
            }
    }

    override suspend fun fetchBookList(resultLimit: Int?): Result<List<Book>, DataError.Remote> {
        return remoteBookListDataSource.fetchBookList().map {
            it.reading_log_entries?.map { it.work.toBook() } ?: emptyList()
        }
    }

//    override fun getFavBookList(): Flow<List<Book>> {
//        return daoBook.getFavBooks()
//            .map {
//                it.map {
//                    it.toBook()
//                }
//            }
//    }
//
//    override fun isBookFav(id: String): Flow<Boolean> {
//        return daoBook.getFavBooks().map { it.any { it.id == id } }
//    }
//
//    override suspend fun toggleFav(book: Book): EmptyResult<DataError.Local> {
//        return if (isBookFav(book.id).first()){
//            daoBook.deleteFavouriteBook(book.id)
//            Result.Success(Unit)
//        } else {
//            try {
//                daoBook.upsert(book.toBookEntity())
//                Result.Success(Unit)
//            }catch (e:SQLiteException){
//                e.printStackTrace()
//                Result.Error(DataError.Local.DISK_FULL)
//            }
//        }
//    }
}