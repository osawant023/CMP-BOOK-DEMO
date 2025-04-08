package com.plcoding.bookpedia.book.data.remote.source

import com.plcoding.bookpedia.book.data.remote.model.DtoResponseBookList
import com.plcoding.bookpedia.book.data.remote.model.DtoResponseSearchBook
import com.plcoding.bookpedia.core.data.remote.WebService
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import safeCall

class KtorRemoteBookListDataSource(private val httpClient: HttpClient) : RemoteSourceBookList {

    override suspend fun fetchBookList(): Result<DtoResponseBookList, DataError.Remote> {
        return safeCall {
            httpClient.get {
                url(WebService.getBookListUrl())
            }
        }
    }

    override suspend fun fetchSearchBooks(
        query: String,
        resultLimit: Int?
    ): Result<DtoResponseSearchBook, DataError.Remote> {
        return safeCall {
            httpClient.get {
                url(WebService.getSearchBookUrl())
                parameter("q", query)
                parameter("limit", query)
                parameter("language", "eng")
                parameter("fields", "key,title,author_name,author_key,cover_edition_key,cover_i,ratings_average,ratings_count,first_publish_year,language,number_of_pages_median,edition_count")
            }
        }
    }
}