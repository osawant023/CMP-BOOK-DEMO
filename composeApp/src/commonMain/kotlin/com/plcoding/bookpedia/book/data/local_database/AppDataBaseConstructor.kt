package com.plcoding.bookpedia.book.data.local_database

import androidx.room.RoomDatabaseConstructor


@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDataBaseConstructor : RoomDatabaseConstructor<AppDataBase> {
    override fun initialize(): AppDataBase
}