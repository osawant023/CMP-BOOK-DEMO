package com.plcoding.bookpedia.book.data.local_database.converter

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object RoomConverter {

    @TypeConverter
    fun stringToList(string: String):List<String>{
        return Json.decodeFromString(string)
    }

    @TypeConverter
    fun listToString(list: List<String>):String{
        return Json.encodeToString(list)
    }
}