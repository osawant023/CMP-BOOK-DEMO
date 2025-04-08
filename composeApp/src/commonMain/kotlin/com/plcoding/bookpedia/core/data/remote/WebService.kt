package com.plcoding.bookpedia.core.data.remote

object WebService {

    private const val BASE_URL= "https://openlibrary.org/"
    private const val WS_BOOK_LIST = "people/mekBot/books/want-to-read.json"
    private const val WS_SEARCH_BOOK = "search.json"

    fun getBookListUrl() : String = buildString {
        append(BASE_URL)
        append(WS_BOOK_LIST)
    }

    fun getSearchBookUrl() : String = buildString {
        append(BASE_URL)
        append(WS_SEARCH_BOOK)
    }
}