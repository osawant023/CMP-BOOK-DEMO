package com.plcoding.bookpedia.book.data.local_database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class AppDataBaseFactory (
    private val context: Context
){
    actual fun create(): RoomDatabase.Builder<AppDataBase> {
        val appContext  = context.applicationContext
        val dbFile = appContext.getDatabasePath(AppDataBase.DATABASE_NAME)
        return Room.databaseBuilder(appContext , dbFile.absolutePath)
    }
}