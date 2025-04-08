package com.plcoding.bookpedia.book.data.remote.model

import com.plcoding.bookpedia.book.domain.model.Book
import kotlinx.serialization.Serializable

@Serializable
data class DtoResponseBookList(
    val numFound: Int? = null,
    val page: Int? = null,
    val reading_log_entries: ArrayList<ReadingLogEntry>? = null
)

@Serializable
data class ReadingLogEntry(
    val logged_date: String? = null,
    val logged_edition: String? = null,
    val work: Work
)

@Serializable
data class Work(
    val author_keys: ArrayList<String>? = null,
    val author_names: ArrayList<String>? = null,
    val cover_edition_key: String? = null,
    val cover_id: Int? = null,
    val edition_key: ArrayList<String>? = null,
    val first_publish_year: Int? = null,
    val key: String? = null,
    val lending_edition_s: String? = null,
    val title: String? = null
){
    fun toBook(): Book {
        return Book(
            id = key?.substringAfterLast("/", key) ?: "",
            title = title ?: "",
            imageUrl = if (cover_edition_key != null) {
                "https://covers.openlibrary.org/b/olid/${cover_edition_key}-L.jpg"
            } else {
                "https://covers.openlibrary.org/b/id/${cover_id}-L.jpg"
            },
            authors = author_names ?: emptyList(),
            description = null,
            firstPublishYear = first_publish_year.toString(),
            averageRating = 0.0,
            ratingCount = 0,
            numPages = 1,
            numEditions = lending_edition_s.run {
                try {
                    this?.toInt() ?: 1
                } catch (e: Exception) {
                    1
                }
            },
            languages = listOf("eng")
        )
    }
}