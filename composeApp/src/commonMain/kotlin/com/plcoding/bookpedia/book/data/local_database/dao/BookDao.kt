package com.plcoding.bookpedia.book.data.local_database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.plcoding.bookpedia.book.data.local_database.entity.BookEntity
import com.plcoding.bookpedia.book.domain.model.Book
import kotlinx.coroutines.flow.Flow


@Dao
interface BookDao {

    @Upsert
    suspend fun upsert(book: BookEntity)

    @Query("SELECT * From BookEntity")
    fun getFavBooks():Flow<List<BookEntity>>

    @Query("SELECT * from BookEntity WHERE id =:id")
    suspend fun getFavouriteBook(id:String): BookEntity

    @Query("DELETE from BookEntity WHERE id =:id")
    suspend fun deleteFavouriteBook(id:String)
}