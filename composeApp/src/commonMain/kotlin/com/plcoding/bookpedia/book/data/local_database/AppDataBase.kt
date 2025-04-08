package com.plcoding.bookpedia.book.data.local_database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.plcoding.bookpedia.book.data.local_database.converter.RoomConverter
import com.plcoding.bookpedia.book.data.local_database.dao.BookDao
import com.plcoding.bookpedia.book.data.local_database.entity.BookEntity

@TypeConverters(
    RoomConverter::class
)
@Database(
    entities = [BookEntity::class],
    version = 1
)
@ConstructedBy(AppDataBaseConstructor::class)
abstract class AppDataBase : RoomDatabase() {

    abstract val favouriteBookDao: BookDao

    companion object {
        const val DATABASE_NAME = "bookpedia.db"
    }
}