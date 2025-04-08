package com.plcoding.bookpedia.book.data.local_database

import androidx.room.RoomDatabase

expect class AppDataBaseFactory {
    fun create():RoomDatabase.Builder<AppDataBase>
}