package com.plcoding.bookpedia.book.data.local_database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class AppDataBaseFactory {
    actual fun create(): RoomDatabase.Builder<AppDataBase> {
        val os = System.getProperty("os.name").lowercase()
        val useHome = System.getProperty("user.home")
        val appDataDir = when{
            os.contains("win") -> File(System.getenv("APPDATA"), "Bookpedia")
            os.contains("mac") -> File(useHome, "Library/Application Support/Bookpedia")
            else -> File(useHome, ".local/share/Bookpedia")
        }

        if (!appDataDir.exists()){
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir , AppDataBase.DATABASE_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}