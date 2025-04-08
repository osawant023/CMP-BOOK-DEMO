package com.plcoding.bookpedia.book.domain.model

import com.plcoding.bookpedia.book.data.local_database.entity.BookEntity
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Book(
    val id: String,
    val title: String,
    val imageUrl: String,
    val authors: List<String>,
    val description: String?,
    val languages: List<String>,
    val firstPublishYear: String?,
    val averageRating: Double?,
    val ratingCount: Int?,
    val numPages: Int?,
    val numEditions: Int
){
    fun toBookEntity(): BookEntity{
        return BookEntity(
            id =  id ,
            title =  title ,
            imageUrl =  imageUrl ,
            authors =  authors ,
            description =  description ,
            languages =  languages ,
            firstPublishYear =  firstPublishYear ,
            averageRating =  averageRating ,
            ratingCount =  ratingCount ,
            numPages =  numPages ,
            numEditions = numEditions,
        )
    }
}

fun Book.toJsonString():String{
    return Json.encodeToString(this)
}


