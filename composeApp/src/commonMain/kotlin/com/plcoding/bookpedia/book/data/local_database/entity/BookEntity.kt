package com.plcoding.bookpedia.book.data.local_database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.plcoding.bookpedia.book.domain.model.Book

@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
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
    fun toBook(): Book {
        return Book(
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



