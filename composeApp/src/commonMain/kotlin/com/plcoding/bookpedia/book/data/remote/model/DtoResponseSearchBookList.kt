package com.plcoding.bookpedia.book.data.remote.model

import com.plcoding.bookpedia.book.domain.model.Book
import kotlinx.serialization.Serializable

@Serializable
data class DtoResponseSearchBook(
    val docs: List<DtoSearchBook>? = emptyList()
)

@Serializable
data class DtoSearchBook(
    val author_key: List<String>? = null,
    val author_name: List<String>? = null,
    val cover_edition_key: String? = null,
    val cover_i: Int? = null,
    val edition_count: Int? = null,
    val first_publish_year: Int? = null,
    val has_fulltext: Boolean? = null,
    val ia: List<String?>? = null,
    val ia_collection_s: String? = null,
    val id_project_gutenberg: List<String>? = null,
    val key: String? = null,
    val language: List<String>? = null,
    val lending_edition_s: String? = null,
    val lending_identifier_s: String? = null,
    val public_scan_b: Boolean? = null,
    val subtitle: String? = null,
    val title: String? = null,
    val ratings_average: Double? = null,
    val ratings_count: Int? = null,
    val number_of_pages_median: Int? = null,
){
    fun toBook(): Book {
        return Book(
            id = key?.substringAfterLast("/", key) ?: "",
            title = title ?: "",
            imageUrl = if (cover_edition_key != null) {
                "https://covers.openlibrary.org/b/olid/${cover_edition_key}-L.jpg"
            } else {
                "https://covers.openlibrary.org/b/id/${cover_i}-L.jpg"
            },
            authors = author_name ?: emptyList(),
            description = null,
            languages = language ?: emptyList(),
            firstPublishYear = first_publish_year.toString(),
            averageRating = ratings_average,
            ratingCount = ratings_count,
            numPages = number_of_pages_median,
            numEditions = edition_count.run {
                try {
                    this ?: 1
                } catch (e: Exception) {
                    1
                }
            }
        )
    }
}